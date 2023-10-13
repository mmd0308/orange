<template>
  <div>
    <tiny-drawer :title="title" :visible="visible" :show-footer="true" @close="onClose(false)">
      <tiny-form ref="formDataRef" class="tiny-drawer-body-form" label-position="left" :rules="formDataRules"
        :model="formData" label-width="100px" validate-position="bottom" validate-type="text">
        <tiny-form-item label="部门" prop="parentId">
          <tiny-select v-model="formData.departmentId" value-field="id" text-field="name" render-type="tree"
            :tree-op="treeOp" :placeholder="$t('system.department.form.parentId.placeholder')"></tiny-select>
        </tiny-form-item>
        <tiny-form-item label="用户名称" prop="name">
          <tiny-input v-model="formData.name"></tiny-input>
        </tiny-form-item>
        <tiny-form-item label="邮箱" prop="email">
          <tiny-input v-model="formData.email"></tiny-input>
        </tiny-form-item>
        <tiny-form-item label="性别" prop="sex">
          <tiny-radio v-for="(item, index) in proxy.$dict.getDict('sys_common_user_sex')" :key="index"
            v-model="formData.sex" :label="item.dictValue">{{ item.dictLabel }}</tiny-radio>
        </tiny-form-item>
        <tiny-form-item label="手机号" prop="phone">
          <tiny-input v-model="formData.phone"></tiny-input>
        </tiny-form-item>
        <tiny-form-item label="登陆账号" prop="username">
          <tiny-input v-model="formData.username"></tiny-input>
        </tiny-form-item>
        <tiny-form-item label="备注" prop="remark">
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
import { listDepartmentToTree } from '@/utils/department/index'

const emit = defineEmits(['ok'])

const { proxy } = getCurrentInstance() as any

const visible = ref(false)
const isModify = ref(false)
const title = computed(() => {
  return isModify.value ? '修改用户' : '新增用户'
})

const formData = ref<SystemPermissionAPI.UserVO>({
  name: '',
})

const formDataRules = {
  name: [{ required: true, message: '用户名称不能为空', trigger: 'change' }],
  permission: [{ required: true, message: '权限编码不能为空', trigger: 'change' }]
}


const onSubmit = () => {
  proxy.$refs.formDataRef.validate((valid: boolean) => {
    if (valid) {
      if (formData.value.id) {
        SystemRequest.user
          .updateUserById(formData.value.id, toRaw(formData.value))
          .then((res) => {
            proxy.$modal.message({ message: '修改成功', status: 'success' });
            onClose(true)
          })
          .catch((err) => { console.log(err) })
      } else {
        SystemRequest.user
          .addUser(toRaw(formData.value))
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

const treeOp = reactive<{
  data: SystemPermissionAPI.DepartmentTreeVO[]
}>({
  data: []
})

const queryAll = (query: SystemPermissionAPI.DepartmentAllQuery) => {
  SystemRequest.department.queryDepartmentAll(toRaw(query)).then((res) => {
    treeOp.data = listDepartmentToTree(res.data)
  })
}


const open = (id: string) => {
  isModify.value = false
  if (id) {
    SystemRequest.user.getUserById(id).then((response) => {
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