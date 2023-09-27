<template>
  <div>
    <tiny-drawer :title="title" :visible="visible" :show-footer="true" @close="onClose(false)">
      <tiny-form ref="formDataRef" class="tiny-drawer-body-form" label-position="left" :rules="formDataRules"
        :model="formData" label-width="100px" validate-position="bottom" validate-type="text">
        <tiny-form-item :label="$t('system.department.form.parentId')" prop="parentId">
          <tiny-select v-model="formData.parentId" value-field="id" text-field="name" render-type="tree" :tree-op="treeOp"
            :placeholder="$t('system.department.form.parentId.placeholder')"></tiny-select>
        </tiny-form-item>
        <tiny-form-item :label="$t('system.department.form.name')" prop="name">
          <tiny-input v-model="formData.name" :placeholder="$t('system.department.form.name.placeholder')"></tiny-input>
        </tiny-form-item>
        <tiny-form-item :label="$t('global.form.sort')" prop="sort">
          <tiny-numeric v-model="formData.sort"></tiny-numeric>
        </tiny-form-item>
      </tiny-form>
      <template #footer>
        <tiny-button type="primary" @click="onSubmit">保存</tiny-button>
        <tiny-button @click="onClose(false)">取消</tiny-button>
      </template>
    </tiny-drawer>
  </div>
</template>

<script lang="ts" setup>
import SystemRequest from '@/api/system/index'

const emit = defineEmits(['ok'])
const { proxy } = getCurrentInstance() as any
const visible = ref(false)
const isModify = ref(false)
const title = computed(() => {
  return isModify.value ? '修改部门' : '新增部门'
})

const formData = ref<SystemPermissionAPI.DepartmentVO>({
  parentId: '-1',
  name: '',
  sort: 1,
})

const formDataRules = {
  parentId: [{ required: true, message: '上级部门不能为空', trigger: 'change' }],
  name: [{ required: true, message: '部门名称不能为空', trigger: 'change' }]
}

const onSubmit = () => {
  proxy.$refs.formDataRef.validate((valid: boolean) => {
    if (valid) {
      if (formData.value.id) {
        SystemRequest.department
          .updateDepartmentById(formData.value.id, toRaw(formData.value))
          .then((res) => {
            proxy.$modal.message({ message: '修改成功', status: 'success' });
            onClose(true)
          })
          .catch((err) => { console.log(err) })
      } else {
        SystemRequest.department
          .addDepartment(toRaw(formData.value))
          .then((res) => {
            proxy.$modal.message({ message: '创建成功', status: 'success' });
            onClose(true)
          })
          .catch((err) => { console.log(err) })
      }
    }
  })
}

const onClose = (refresh: boolean) => {
  visible.value = false
  formData.value = {}
  if (refresh) {
    emit('ok')
  }
}

const treeOp = reactive<{
  data: [{
    id: string,
    name: string,
    children: SystemPermissionAPI.DepartmentTreeVO[]
  }]
}>({
  data: [{
    id: '-1',
    name: '根节点',
    children: []
  }]
})

const queryAll = (query: SystemPermissionAPI.DepartmentAllQuery) => {
  SystemRequest.department.queryDepartmentAll(toRaw(query)).then((res) => {
    treeOp.data[0].children = aggregateTableData(res.data)
  })
}

function aggregateTableData(data: SystemPermissionAPI.DepartmentTreeVO[]) {
  const result: SystemPermissionAPI.DepartmentTreeVO[] = []
  data.forEach((item) => {
    if (item.parentId === '-1') {
      result.push(item)
    } else {
      const parent = data.find((i) => i.id === item.parentId)
      if (!parent) return
      if (!parent.children) {
        parent.children = []
      }
      parent.children.push(item)
    }
  })
  return result
}

const open = (data: SystemPermissionAPI.DepartmentVO) => {
  isModify.value = false
  if (data) {
    formData.value = data
    isModify.value = true
  }
  queryAll({})
  visible.value = true
}

defineExpose({
  open
})
</script>