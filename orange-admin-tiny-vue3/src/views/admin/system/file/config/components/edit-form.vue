<template>
  <tiny-drawer id="tinyDrawer" :title="title" :visible="visible" :show-footer="true" @close="onClose(false)">
    <tiny-form ref="formDataRef" class="tiny-drawer-body-form" label-position="left" :rules="formDataRules"
      :model="formData" label-width="130px" validate-position="bottom" validate-type="text">
      <tiny-form-item :label="$t('system.file.config.form.name')" prop="name">
        <tiny-input v-model="formData.name" :placeholder="$t('system.file.config.form.name.placeholder')"></tiny-input>
      </tiny-form-item>
      <tiny-form-item :label="$t('system.file.config.form.storage')" prop="storage">
        <tiny-select v-model="formData.storage" :placeholder="$t('system.file.config.form.storage.placeholder')">
          <tiny-option v-for="it in storages" :key="it.key" :label="it.name" :value="it.key"> </tiny-option>
        </tiny-select>
      </tiny-form-item>
      <tiny-form-item v-if="formData.storage === 'LOCAL'" :label="$t('system.file.config.form.basePath')" prop="basePath">
        <tiny-input v-model="formData.basePath"
          :placeholder="$t('system.file.config.form.basePath.placeholder')"></tiny-input>
      </tiny-form-item>

      <template v-if="formData.storage === 'ALIYUN'">
        <tiny-form-item :label="$t('system.file.config.form.endPoint')" prop="endPoint">
          <tiny-input v-model="formData.endPoint"
            :placeholder="$t('system.file.config.form.endPoint.placeholder')"></tiny-input>
        </tiny-form-item>
        <tiny-form-item :label="$t('system.file.config.form.bucketName')" prop="bucketName">
          <tiny-input v-model="formData.bucketName"
            :placeholder="$t('system.file.config.form.bucketName.placeholder')"></tiny-input>
        </tiny-form-item>
        <tiny-form-item :label="$t('system.file.config.form.accessKey')" prop="accessKey">
          <tiny-input v-model="formData.accessKey"
            :placeholder="$t('system.file.config.form.accessKey.placeholder')"></tiny-input>
        </tiny-form-item>
        <tiny-form-item :label="$t('system.file.config.form.accessKeySecret')" prop="accessKeySecret">
          <tiny-input v-model="formData.accessKeySecret"
            :placeholder="$t('system.file.config.form.accessKeySecret.placeholder')"></tiny-input>
        </tiny-form-item>
      </template>


      <tiny-form-item :label="$t('system.file.config.form.domain')" prop="domain">
        <tiny-input v-model="formData.domain"
          :placeholder="$t('system.file.config.form.domain.placeholder')"></tiny-input>
      </tiny-form-item>
      <tiny-form-item :label="$t('global.form.remark')" prop="remark">
        <tiny-input v-model="formData.remark" :placeholder="$t('global.form.remark.placeholder')" type="textarea"
          :maxlength="500" :rows="5" show-word-limit>
        </tiny-input>
      </tiny-form-item>
    </tiny-form>

    <template #footer>
      <tiny-button type="primary" @click="onSubmit">保存</tiny-button>
      <tiny-button @click="onClose(false)">取消</tiny-button>
    </template>
  </tiny-drawer>
</template>

<script lang="ts" setup>
import SystemRequest from '@/api/system/index'

const { proxy } = getCurrentInstance() as any

const emit = defineEmits(['ok'])
const visible = ref(false)
const isModify = ref(false)
const title = computed(() => {
  return isModify.value ? '修改配置' : '新增配置'
})

const formData = ref<SystemFileAPI.ConfigVO>({})
const initFromData = () => {
  formData.value = {}
}

const formDataRules = {
  name: [{ required: true, message: '配置名称不能为空', trigger: 'change' }],
  storage: [{ required: true, message: '请选择存储器', trigger: 'change' }]
}

const onSubmit = () => {
  proxy.$refs.formDataRef.validate((valid: boolean) => {
    if (valid) {
      if (formData.value.id) {
        SystemRequest.fileConfig
          .updateConfigById(formData.value.id, toRaw(formData.value))
          .then((res) => {
            proxy.$modal.message({ message: '修改成功', status: 'success' });
            onClose(true)
          })
          .catch((err) => { console.log(err) })
      } else {
        SystemRequest.fileConfig
          .addConfig(toRaw(formData.value))
          .then((res) => {
            proxy.$modal.message({ message: '创建成功', status: 'success' });
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

const storages = ref<SystemFileAPI.Storage[]>([])
const open = (id: string) => {
  isModify.value = false
  initFromData()
  const item = sessionStorage.getItem("file_config_storage")
  if (item) {
    storages.value = JSON.parse(item)
  }
  if (id) {
    SystemRequest.fileConfig.getConfigById(id).then((response) => {
      const { name, storage, remark, config } = response.data
      formData.value.id = id
      formData.value.name = name
      formData.value.storage = storage
      formData.value.remark = remark
      formData.value.basePath = config.basePath
      formData.value.domain = config.domain
      formData.value.endPoint = config.endPoint
      formData.value.bucketName = config.bucketName
      formData.value.accessKey = config.accessKey
      formData.value.accessKeySecret = config.accessKeySecret
      isModify.value = true
    })
  }
  visible.value = true
}

defineExpose({
  open
})
</script>