<template>
  <div>
    <tiny-drawer :title="title" :visible="visible" :show-footer="true" @close="onClose(false)">
      <tiny-form ref="formDataRef" class="tiny-drawer-body-form" label-position="left" :rules="formDataRules"
        :model="formData" label-width="100px">
        <tiny-form-item :label="$t('system.role.form.name')" prop="name">
          <tiny-input v-model="formData.name" :placeholder="$t('system.role.form.name.placeholder')"></tiny-input>
        </tiny-form-item>
        <tiny-form-item :label="$t('system.role.form.permission')" prop="permission">
          <tiny-input v-model="formData.permission"
            :placeholder="$t('system.role.form.permission.placeholder')"></tiny-input>
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
  Form as TinyForm, FormItem as TinyFormItem,
  Input as TinyInput,
} from '@opentiny/vue'

import SystemRequest from '@/api/system/index'

const emit = defineEmits(['ok'])

const { proxy } = getCurrentInstance() as any

const visible = ref(false)
const isModify = ref(false)
const title = computed(() => {
  return isModify.value ? '修改角色' : '新增角色'
})

const formData = ref<SystemPermissionAPI.RoleVO>({
  name: '',
  permission: '',
})

const formDataRules = {
  name: [{ required: true, message: '角色名称不能为空', trigger: 'change' }],
  permission: [{ required: true, message: '权限编码不能为空', trigger: 'change' }]
}

const onSubmit = () => {
  proxy.$refs.formDataRef.validate((valid: boolean) => {
    if (valid) {
      if (formData.value.id) {
        SystemRequest.role
          .updateRoleById(formData.value.id, toRaw(formData.value))
          .then((res) => {
            onClose(true)
          })
          .catch((err) => { console.log(err) })
      } else {
        SystemRequest.role
          .addRole(toRaw(formData.value))
          .then((res) => {
            onClose(true)
          })
          .catch((err) => { console.log(err) })
      }
    }
  })
}

const onClose = (refresh?: boolean) => {
  visible.value = false
  proxy.$refs.formDataRef.resetFields()
  if (refresh) {
    emit('ok')
  }
}

const open = (id: string) => {
  isModify.value = false
  if (id) {
    SystemRequest.role.getRoleById(id).then((response) => {
      formData.value = response.data
      isModify.value = true
    })
  }
  visible.value = true
}

defineExpose({
  open
})
</script>