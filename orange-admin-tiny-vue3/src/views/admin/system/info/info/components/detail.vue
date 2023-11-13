<template>
  <tiny-drawer id="tinyDrawer" :title="title" :visible="visible" width="70%" @close="onClose">
    <tiny-form ref="formDataRef" class="tiny-drawer-body-form info-form" :model="formData" label-width="0px"
      validate-position="bottom" validate-type="text">
      <tiny-form-item prop="title">
        <tiny-input v-model="formData.title" size="medium" readonly
          :placeholder="$t('system.info.form.title.placeholder')"></tiny-input>
      </tiny-form-item>
      <tiny-form-item prop="content">
        <Editor v-model="formData.content" :default-config="editorConfig" style="height: 760px; overflow-y: hidden;"
          @on-created="handleCreated" />
      </tiny-form-item>
    </tiny-form>
  </tiny-drawer>
</template>

<script lang="ts" setup>
import '@wangeditor/editor/dist/css/style.css'

import SystemRequest from '@/api/system/index'
import { Editor } from '@wangeditor/editor-for-vue'

const { proxy } = getCurrentInstance() as any
const editorRef = shallowRef()
const editorConfig = { readOnly: true }

const visible = ref(false)
const title = computed(() => { return '详情' })

const formData = ref<SystemInfoAPI.InfoVO>({})
const initFromData = () => {
  formData.value = {}
}

const handleCreated = (editor: any) => {
  editorRef.value = editor
}

const onClose = () => {
  visible.value = false
  proxy.$refs.formDataRef.resetFields()
}

const open = (id: string) => {
  initFromData()
  SystemRequest.info.getInfoById(id).then((response) => {
    formData.value = response.data
  })
  visible.value = true
}

defineExpose({
  open
})
</script>

<style lang="less" >
.info-form {
  .tiny-input__inner {
    border: none;
    height: 50px;
    font-size: 20px;
  }
}
</style>