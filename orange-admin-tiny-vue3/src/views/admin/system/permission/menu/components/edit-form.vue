<template>
  <div>
    <tiny-drawer :title="title" :visible="visible" :show-footer="true" @close="onClose(false)">
      <tiny-form ref="formDataRef" class="tiny-drawer-body-form" label-position="left" :rules="formDataRules"
        :model="formData" label-width="100px">
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
import {
  Drawer as TinyDrawer,
  Button as TinyButton,
  Form as TinyForm, FormItem as TinyFormItem, Select as TinySelect,
  Input as TinyInput, Numeric as TinyNumeric
} from '@opentiny/vue'

import SystemRequest from '@/api/system/index'

const emit = defineEmits(['ok'])

const { proxy } = getCurrentInstance() as any

const visible = ref(false)
const isModify = ref(false)
const title = computed(() => {
  return isModify.value ? '修改菜单' : '新增菜单'
})

const formData = ref<SystemPermissionAPI.DepartmentVO>({
  parentId: '-1',
  name: '',
  sort: 1,
})

const formDataRules = {
  parentId: [{ required: true, message: '上级菜单不能为空', trigger: 'change' }],
  name: [{ required: true, message: '菜单名称不能为空', trigger: 'change' }]
}

const onSubmit = () => {
  proxy.$refs.formDataRef.validate((valid: boolean) => {
    if (valid) {
      if (formData.value.id) {
        SystemRequest.menu
          .updateMenuById(formData.value.id, toRaw(formData.value))
          .then((res) => {
            onClose(true)
          })
          .catch((err) => { console.log(err) })
      } else {
        SystemRequest.menu
          .addMenu(toRaw(formData.value))
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

const treeOp = reactive<{
  data: [{
    id: string,
    name: string,
    children: SystemPermissionAPI.MenuTreeVO[]
  }]
}>({
  data: [{
    id: '-1',
    name: '根节点',
    children: []
  }]
})

const queryAll = (query: SystemPermissionAPI.MenuAllQuery) => {
  SystemRequest.menu.queryMenuAll(toRaw(query)).then((res) => {
    treeOp.data[0].children = aggregateTableData(res.data)
  })
}

function aggregateTableData(data: SystemPermissionAPI.MenuTreeVO[]) {
  const result: SystemPermissionAPI.MenuTreeVO[] = []
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
    SystemRequest.menu.getMenuById(id).then((response) => {
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