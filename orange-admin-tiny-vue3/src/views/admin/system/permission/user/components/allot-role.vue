<template>
  <div>
    <tiny-drawer :title="title" :visible="visible" :show-footer="true" @close="onClose">
      <tiny-form ref="formDataRef" class="tiny-drawer-body-form" label-position="left" :model="formData"
        label-width="100px" validate-position="bottom" validate-type="text">
        <tiny-form-item label="用户名称">
          <tiny-input v-model="formData.name" disabled></tiny-input>
        </tiny-form-item>
        <tiny-form-item label="登陆账号">
          <tiny-input v-model="formData.username" disabled></tiny-input>
        </tiny-form-item>
        <tiny-form-item label="角色">
          <tiny-select v-model="roleIds" value-field="id" :multiple="true" text-field="name" render-type="grid"
            :grid-op="gridOp" placeholder="请选择角色"></tiny-select>
        </tiny-form-item>
      </tiny-form>
      <template #footer>
        <tiny-button type="primary" @click="onSubmit">保存</tiny-button>
        <tiny-button @click="onClose">取消</tiny-button>
      </template>
    </tiny-drawer>
  </div>
</template>

<script lang="ts" setup>
import {
  Drawer as TinyDrawer,
  Button as TinyButton,
  Form as TinyForm, FormItem as TinyFormItem,
  Input as TinyInput, Select as TinySelect, Loading
} from '@opentiny/vue'

import SystemRequest from '@/api/system/index'

const { proxy } = getCurrentInstance() as any

const visible = ref(false)
const title = '分配角色'

const formData = ref<SystemPermissionAPI.UserDetailsVO>({
  name: '',
  username: ''
})

const roleIds = ref([])

const gridOp = reactive({
  data: [],
  columns: [
    { type: 'selection', title: '', width: 60 },
    { field: 'name', title: '角色名称' },
    { field: 'permission', title: '权限编码' }
  ]
})
const getAllRole = async () => {
  const { data } = await SystemRequest.role.queryRoleAll({});
  gridOp.data = data
}
const onSubmit = () => {
  let params: SystemPermissionAPI.AllotUserRoleVO = {
    userId: formData.value.id || '',
    roleIds: roleIds.value
  }
  SystemRequest.permission.allotUserRole
    (params)
    .then((res) => {
      onClose()
    })
    .catch((err) => { console.log(err) })
}

const onClose = () => {
  formData.value = {}
  visible.value = false
}

const open = async (id: string) => {
  if (id) {
    await SystemRequest.user.getUserDetailsById(id).then((response) => {
      let user = response.data as SystemPermissionAPI.UserDetailsVO
      formData.value = user
      roleIds.value = user.roleVOList?.map(item => item.id) as []
    })
    getAllRole()
  }
  visible.value = true
}

defineExpose({
  open
})
</script>