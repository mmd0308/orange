<template>
  <tiny-drawer id="tinyDrawer" :title="title" :visible="visible" width="80%" :show-footer="true" @close="onClose(false)">
    <tiny-form ref="formDataRef" class="tiny-drawer-body-form info-form" label-position="left" :rules="formDataRules"
      :model="formData" label-width="0px" validate-position="bottom" validate-type="text">
      <tiny-form-item prop="title">
        <tiny-input v-model="formData.title" size="medium"
          :placeholder="$t('system.info.form.title.placeholder')"></tiny-input>
      </tiny-form-item>
      <tiny-form-item prop="content">
        <Toolbar style="border-bottom: 1px solid #ccc;border-top: 1px solid #ccc;" :editor="editorRef"
          :default-config="toolbarConfig" />
        <Editor v-model="formData.content" :default-config="editorConfig" style="height: 650px; overflow-y: hidden;"
          @on-created="handleCreated" />
      </tiny-form-item>
    </tiny-form>

    <template #footer>
      <tiny-button type="primary" @click="onSubmit">保存</tiny-button>
      <tiny-button @click="onClose(false)">取消</tiny-button>
    </template>
  </tiny-drawer>
</template>

<script lang="ts" setup>
import '@wangeditor/editor/dist/css/style.css'
import SystemRequest from '@/api/system/index'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'

const { proxy } = getCurrentInstance() as any

type InsertFnType = (url: string, alt: string, href: string) => void

const editorRef = shallowRef()
const toolbarConfig = {
  excludeKeys: [
    'group-video'
  ]
}
const editorConfig = {
  placeholder: '请输入内容...',
  MENU_CONF: {
    uploadImage: {
      // 自定义上传
      async customUpload(file: File, insertFn: InsertFnType) {
        let formData = new FormData();
        formData.append("file", file);
        SystemRequest.file.upload(formData).then(res => {
          const { data } = res
          insertFn(data.url, "图片加载异常", "")
        })
      }
    }
  }
}

const emit = defineEmits(['ok'])
const visible = ref(false)
const isModify = ref(false)
const title = computed(() => {
  return isModify.value ? '修改信息' : '新增信息'
})

const formData = ref<SystemInfoAPI.InfoVO>({})

const initFromData = () => {
  formData.value = {
    sort: 1,
  }
}

const formDataRules = {
  title: [{ required: true, message: '标题不能为空', trigger: 'change' }],
}

const onSubmit = () => {
  proxy.$refs.formDataRef.validate((valid: boolean) => {
    if (valid) {
      if (formData.value.id) {
        SystemRequest.info
          .updateInfoById(formData.value.id, toRaw(formData.value))
          .then((res) => {
            proxy.$modal.message({ message: '修改成功', status: 'success' });
            onClose(true)
          })
          .catch((err) => { console.log(err) })
      } else {
        SystemRequest.info
          .addInfo(toRaw(formData.value))
          .then((res) => {
            proxy.$modal.message({ message: '创建成功', status: 'success' });
            onClose(true)
          })
          .catch((err) => { console.log(err) })
      }
    }
  })
}

const handleCreated = (editor: any) => {
  editorRef.value = editor
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
  initFromData()
  if (id) {
    SystemRequest.info.getInfoById(id).then((response) => {
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

<style lang="less">
.info-form {
  .tiny-input__inner {
    border: none;
    height: 50px;
    font-size: 20px;
  }
}
</style>