<template>
  <div>
    <tiny-drawer :title="title" :visible="visible" :show-footer="true" @close="onClose">
      <tiny-form ref="formDataRef" class="tiny-drawer-body-form" label-position="left" :rules="formDataRules"
        :model="formData" label-width="100px">
        <tiny-form-item :label="$t('system.dict-data.form.dictLabel')" prop="name">
          <tiny-input v-model="formData.dictLabel"
            :placeholder="$t('system.dict-data.form.dictLabel.placeholder')"></tiny-input>
        </tiny-form-item>
        <tiny-form-item :label="$t('system.dict-data.form.dictType')" prop="dictType">
          <tiny-input v-model="formData.dictType"
            :placeholder="$t('system.dict-data.form.dictType.placeholder')"></tiny-input>
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
  Form as TinyForm, FormItem as TinyFormItem,
  Input as TinyInput,
} from '@opentiny/vue'

import SystemRequest from '@/api/system/index'

const emit = defineEmits(['ok'])

const { proxy } = getCurrentInstance() as any

const visible = ref(false)
const isModify = ref(false)
const title = computed(() => {
  return isModify.value ? '修改字典类型' : '新增字典类型'
})

const formData = ref<SystemDictAPI.DictDataVO>({
  dictType: '',
  dictLabel: '',
  dictValue: '',
})

const formDataRules = {
  name: [{ required: true, message: '字典类型名称不能为空', trigger: 'change' }],
  dictType: [{ required: true, message: '字典类型不能为空', trigger: 'change' }]
}

const onSubmit = () => {
  proxy.$refs.formDataRef.validate((valid: boolean) => {
    if (valid) {
      if (formData.value.id) {
        SystemRequest.dictData
          .updateDictDataById(formData.value.id, toRaw(formData.value))
          .then((res) => {
            onClose(true)
          })
          .catch((err) => { console.log(err) })
      } else {
        SystemRequest.dictData
          .addDictData(toRaw(formData.value))
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