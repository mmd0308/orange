<template>
  <div>
    <tiny-drawer :title="title" :visible="visible" :show-footer="true" @close="onClose">
      <tiny-form ref="formDataRef" class="tiny-drawer-body-form" label-position="left" :rules="formDataRules"
        :model="formData" label-width="100px">
        <tiny-form-item label="上级部门" prop="parentId">
          <tiny-select v-model="formData.parentId" value-field="id" text-field="name" render-type="tree" :tree-op="treeOp"
            placeholder="请选择上级部门"></tiny-select>
        </tiny-form-item>
        <tiny-form-item label="部门名称" prop="name">
          <tiny-input v-model="formData.name"></tiny-input>
        </tiny-form-item>
      </tiny-form>
      <template #footer>
        <tiny-button type="primary" @click="onSubmit">保存</tiny-button>
        <tiny-button @click="visible = false">取消</tiny-button>
      </template>
    </tiny-drawer>
  </div>
</template>

<script lang="ts" setup>
import {
  Drawer as TinyDrawer,
  Button as TinyButton,
  Form as TinyForm, FormItem as TinyFormItem, Select as TinySelect,
  Input as TinyInput,
} from '@opentiny/vue'

import SystemRequest from '@/api/system/index'

const emit = defineEmits(['ok'])

const { proxy } = getCurrentInstance() as any

const visible = ref(false)
const isModify = ref(false)
const title = computed(() => {
  return isModify.value ? '修改部门' : '新增部门'
})

const formData = ref<SystemPermissionAPI.RoleVO>({
  name: '',
  permission: '',
})

const formDataRules = {
  name: [{ required: true, message: '部门名称不能为空', trigger: 'change' }]
}

const onSubmit = () => {
  proxy.$refs.formDataRef.validate((valid: boolean) => {
    if (valid) {
      if (formData.value.id) {
        SystemRequest.department
          .updateDepartmentById(formData.value.id, toRaw(formData.value))
          .then((res) => {
            onClose(true)
          })
          .catch((err) => { console.log(err) })
      } else {
        SystemRequest.department
          .addDepartment(toRaw(formData.value))
          .then((res) => {
            onClose(true)
          })
          .catch((err) => { console.log(err) })
      }
    }
  })
}

const onClose = (refresh: boolean) => {
  visible.value = false
  proxy.$refs.formDataRef.resetFields()
  if (refresh) {
    emit('ok')
  }
}

const treeOp = reactive({
  data: [{
    id: '-1',
    name: '根节点'
  }]
})

const queryAll = (query: SystemPermissionAPI.DepartmentAllQueryParams) => {
  SystemRequest.department.queryDepartmentAll(toRaw(query)).then((res) => {
    treeOp.data[0].children = aggregateTableData(res.data)
  })
}

function aggregateTableData(data) {
  const result = []
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
    delete item.parentId
  })
  return result
}

const open = (id: string) => {
  isModify.value = false
  if (id) {
    SystemRequest.department.getDepartmentById(id).then((response) => {
      formData.value = response.data
      isModify.value = true
    })
  }
  queryAll({})
  visible.value = true
}

defineExpose({
  open
})
</script>