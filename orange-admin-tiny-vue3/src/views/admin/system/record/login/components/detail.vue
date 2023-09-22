<template>
  <tiny-drawer :title="title" :visible="visible" :show-footer="true" width="45%" @close="onClose">
    <tiny-form class="tiny-drawer-body-form" label-position="left" :display-only="true" :model="formData"
      label-width="100px">
      <tiny-form-item :label="$t('system.record.operation.form.traceId')">
        <tiny-input v-model="formData.traceId"></tiny-input>
      </tiny-form-item>
      <tiny-form-item :label="$t('system.record.operation.form.status')">
        <dict-tag :value="formData.status" :options="proxy.$dict.getDict('sys_common_operation_status')" />
      </tiny-form-item>
      <tiny-form-item :label="$t('system.record.operation.form.startTime')">
        <tiny-input v-model="formData.loginTime"></tiny-input>
      </tiny-form-item>
      <tiny-form-item :label="$t('system.record.operation.form.userIp')">
        <tiny-input v-model="formData.userIp"></tiny-input>
      </tiny-form-item>
      <tiny-form-item :label="$t('system.record.operation.form.userAgent')">
        <tiny-input v-model="formData.userAgent"></tiny-input>
      </tiny-form-item>
    </tiny-form>
    <template #footer>
      <tiny-button @click="onClose">取消</tiny-button>
    </template>
  </tiny-drawer>
</template>

<script lang="ts" setup>
import SystemRequest from '@/api/system/index'

const { proxy } = getCurrentInstance() as any
const visible = ref(false)
const title = '详情'

const formData = ref<SystemRecordAPI.RecordLoginVO>({})

const onClose = () => {
  visible.value = false
}

const open = (id: string) => {
  SystemRequest.recordLogin.getRecordLoginById(id).then((response) => {
    formData.value = response.data
  })
  visible.value = true
}

defineExpose({
  open
})
</script>