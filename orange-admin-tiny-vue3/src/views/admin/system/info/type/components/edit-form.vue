<template>
  <div>
    <tiny-drawer :title="title" :visible="visible" :show-footer="true" @close="onClose(false)">
      <tiny-form ref="formDataRef" class="tiny-drawer-body-form" label-position="left" :rules="formDataRules"
        :model="formData" label-width="100px" validate-position="bottom" validate-type="text">

        <tiny-form-item :label="$t('system.info.type.form.parentId')" prop="parentId">
          <tiny-select v-model="formData.parentId" value-field="id" text-field="name" render-type="tree" :tree-op="treeOp"
            :placeholder="$t('system.info.type.form.parentId.placeholder')"></tiny-select>
        </tiny-form-item>
        <tiny-form-item :label="$t('system.info.type.form.name')" prop="name">
          <tiny-input v-model="formData.name" :placeholder="$t('system.info.type.form.name.placeholder')"></tiny-input>
        </tiny-form-item>
        <tiny-form-item :label="$t('system.info.type.form.code')" prop="code">
          <tiny-input v-model="formData.code" :placeholder="$t('system.info.type.form.code.placeholder')"></tiny-input>
        </tiny-form-item>
        <tiny-form-item :label="$t('global.form.sort')" prop="sort">
          <tiny-numeric v-model="formData.sort"></tiny-numeric>
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

const { proxy } = getCurrentInstance() as any
const emit = defineEmits(['ok'])

const visible = ref(false)
const isModify = ref(false)
const title = computed(() => {
  return isModify.value ? '修改类型' : '新增类型'
})

const formData = ref<SystemInfoAPI.InfoTypeVO>({})

const initFromData = () => {
  formData.value = {
    sort: 1,
  }
}

const formDataRules = {
  parentId: [{ required: true, message: '上级类型不能为空', trigger: 'change' }],
  name: [{ required: true, message: '类型名称不能为空', trigger: 'change' }],
  code: [{ required: true, message: '类型编码不能为空', trigger: 'change' }]
}

const onSubmit = () => {
  proxy.$refs.formDataRef.validate((valid: boolean) => {
    if (valid) {
      if (formData.value.id) {
        SystemRequest.infoType
          .updateInfoTypeById(formData.value.id, toRaw(formData.value))
          .then((res) => {
            proxy.$modal.message({ message: '修改成功', status: 'success' });
            onClose(true)
          })
          .catch((err) => { console.log(err) })
      } else {
        SystemRequest.infoType
          .addInfoType(toRaw(formData.value))
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
  proxy.$refs.formDataRef.resetFields()
  if (refresh) {
    emit('ok')
  }
}

const treeOp = reactive<{
  data: [{
    id: string,
    name: string,
    children: SystemInfoAPI.InfoTypeTreeVO[]
  }]
}>({
  data: [{
    id: '-1',
    name: '根节点',
    children: []
  }]
})

const queryAll = (query: SystemInfoAPI.InfoTypeAllQuery) => {
  SystemRequest.infoType.queryInfoTypeAll(toRaw(query)).then((res) => {
    treeOp.data[0].children = aggregateTableData(res.data)
  })
}

function aggregateTableData(data: SystemInfoAPI.InfoTypeTreeVO[]) {
  const result: SystemInfoAPI.InfoTypeTreeVO[] = []
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
  initFromData()
  if (id) {
    SystemRequest.infoType.getInfoTypeById(id).then((response) => {
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