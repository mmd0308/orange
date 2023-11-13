<template>
  <tiny-drawer :title="title" :visible="visible" :show-footer="true" width="48%" @close="onClose">
    <tiny-form ref="formDataRef" class="tiny-drawer-body-form" label-position="left" :model="formData" label-width="100px"
      validate-position="bottom" :display-only="true" validate-type="text">
      <tiny-row>
        <tiny-col :span="6">
          <tiny-form-item :label="$t('system.role.form.name')" prop="name">
            <tiny-input v-model="formData.name" :placeholder="$t('system.role.form.name.placeholder')"></tiny-input>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="6">
          <tiny-form-item :label="$t('system.role.form.permission')" prop="permission">
            <tiny-input v-model="formData.permission"
              :placeholder="$t('system.role.form.permission.placeholder')"></tiny-input>
          </tiny-form-item>
        </tiny-col>
      </tiny-row>
    </tiny-form>

    <tiny-grid ref="gridTableRef" max-height="750px" :data="tableDataTree" :loading="loading"
      :tree-config="{ children: 'children' }" :auto-resize="true" :select-config="{ checkStrictly: true }">
      <tiny-grid-column field="index" width="30" tree-node></tiny-grid-column>
      <tiny-grid-column type="selection" width="40"></tiny-grid-column>
      <tiny-grid-column field="name" :title="$t('system.menu.table.columns.name')" width="120" />
      <tiny-grid-column field="permission" :title="$t('system.menu.table.columns.permission')">
        <template #default="scope">
          <tiny-checkbox-group>
            <tiny-checkbox v-for="item in scope.row.buttonVOList" :key="item.id" v-model="checkedButtonIds"
              :label="item.id" @change="(it: any) => checkedButton(it, scope.row.id)">
              {{ item.name }}
            </tiny-checkbox>
          </tiny-checkbox-group>
        </template>
      </tiny-grid-column>
    </tiny-grid>

    <template #footer>
      <tiny-button type="primary" @click="onSubmit">保存</tiny-button>
      <tiny-button @click="onClose">取消</tiny-button>
    </template>
  </tiny-drawer>
</template>

<script lang="ts" setup>
import SystemRequest from '@/api/system/index'

const { proxy } = getCurrentInstance() as any
const visible = ref(false);

const checkedButtonIds = ref([])

const title = computed(() => { return '分配菜单权限' })

const tableDataTree = ref<SystemPermissionAPI.MenuTreeVO[]>([])
const tableDataList = ref<SystemPermissionAPI.MenuTreeVO[]>([])

const loading = ref(false)

async function getAllData() {
  loading.value = true;
  try {
    const { data } = await SystemRequest.menu.queryMenuAll({ includeButton: true });
    tableDataTree.value = aggregateTableData(data)
    tableDataList.value = data
  } finally {
    loading.value = false
  }
}
getAllData()

function aggregateTableData(data: SystemPermissionAPI.MenuTreeVO[]) {
  const result: SystemPermissionAPI.MenuTreeVO[] = []
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

const checkedButton = (checked: boolean, menuId: string) => {
  if (checked) {
    let menus = proxy.$refs.gridTableRef.getSelectRecords() as SystemPermissionAPI.MenuVO[]
    let menuIds = menus.map(item => item.id) as string[]
    if (menuIds.length == 0 || !menuIds.includes(menuId)) {
      proxy.$refs.gridTableRef.setSelection(tableDataList.value.filter((item) => menuId == item.id), true)
    }
  }
}

const formData = ref<SystemPermissionAPI.RoleVO>({
  name: '',
})

const onSubmit = () => {
  let menus = proxy.$refs.gridTableRef.getSelectRecords() as SystemPermissionAPI.MenuVO[]

  const form: SystemPermissionAPI.AllotRoleResourceRequest = {
    roleId: formData.value.id || '',
    menuIds: menus.map(item => item.id) as string[],
    buttonIds: checkedButtonIds.value
  }
  SystemRequest.permission.allotRoleResource(form).then((res) => {
    onClose()
    loading.value = false
    proxy.$modal.message({ message: '分配资源成功', status: 'success' });
  }).catch((err) => {
    loading.value = false
  })
}

const onClose = () => {
  visible.value = false
  checkedButtonIds.value = []
  proxy.$refs.gridTableRef.clearSelection();
}

const open = (data: SystemPermissionAPI.RoleVO) => {
  formData.value = data
  if (data.id) {
    SystemRequest.permission.queryResourceIdsByRoleId(data.id).then((response) => {
      const { buttonIds, menuIds } = response.data
      if (menuIds) {
        proxy.$refs.gridTableRef.setSelection(tableDataList.value.filter((item) => menuIds.includes(item.id)), true)
      }
      checkedButtonIds.value = buttonIds
    })
  }
  visible.value = true
}

defineExpose({
  open
})
</script>