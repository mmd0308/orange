<template>
  <div>
    <tiny-drawer :title="title" :visible="visible" :show-footer="true" @close="onClose(false)">
      <tiny-form ref="formDataRef" class="tiny-drawer-body-form" label-position="left" :rules="formDataRules"
        :model="formData" label-width="100px" validate-position="bottom" validate-type="text">
        <tiny-form-item :label="$t('system.dict-type.form.name')" prop="name">
          <tiny-input v-model="formData.name" :placeholder="$t('system.dict-type.form.name.placeholder')"></tiny-input>
        </tiny-form-item>
        <tiny-form-item :label="$t('system.dict-type.form.dictType')" prop="dictType">
          <tiny-input v-model="formData.dictType"
            :placeholder="$t('system.dict-type.form.dictType.placeholder')"></tiny-input>
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
  </div>
</template>

<script lang="ts" setup>
import SystemRequest from '@/api/system/index'

const emit = defineEmits(['ok'])
const { proxy } = getCurrentInstance() as any
const visible = ref(false)
const isModify = ref(false)
const title = computed(() => {
  return isModify.value ? '修改字典类型' : '新增字典类型'
})

const formData = ref<SystemDictAPI.DictTypeVO>({
  name: '',
  dictType: '',
})

const formDataRules = {
  name: [{ required: true, message: '字典类型名称不能为空', trigger: 'change' }],
  dictType: [{ required: true, message: '字典类型不能为空', trigger: 'change' }]
}

const onSubmit = () => {
  proxy.$refs.formDataRef.validate((valid: boolean) => {
    if (valid) {
      if (formData.value.id) {
        SystemRequest.dictType
          .updateDictTypeById(formData.value.id, toRaw(formData.value))
          .then((res) => {
            proxy.$modal.message({ message: '修改成功', status: 'success' });
            onClose(true)
          })
          .catch((err) => { console.log(err) })
      } else {
        SystemRequest.dictType
          .addDictType(toRaw(formData.value))
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
  proxy.$refs.formDataRef.resetFields()
  if (refresh) {
    emit('ok')
  }
}

const open = (id: string) => {
  isModify.value = false
  if (id) {
    SystemRequest.dictType.getDictTypeById(id).then((response) => {
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