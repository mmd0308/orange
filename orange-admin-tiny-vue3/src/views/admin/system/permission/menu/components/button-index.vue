<template>
  <tiny-drawer :title="title" :visible="visible" width="48%" @close="onClose()">
    <div class="container-list">
      <div class="table-scroll">
        <div class="table-wrapper">
          <tiny-grid ref="gridTableRef" max-height="580px" :data="tableData" :loading="loading" :auto-resize="true"
            :edit-config="{ trigger: 'manual', mode: 'row', autoClear: false }"
            @toolbar-button-click="toolbarButtonClickEvent">
            <template #toolbar>
              <tiny-grid-toolbar :buttons="toolbarButtons" full-screen :setting="{ simple: true }" />
            </template>
            <tiny-grid-column type="selection" width="50"></tiny-grid-column>
            <tiny-grid-column field="name" :title="$t('system.button.table.columns.name')" width="100"
              :editor="{ component: 'input' }" />
            <tiny-grid-column field="permission" :title="$t('system.button.table.columns.permission')" width="260"
              :editor="{ component: 'input' }" />
            <tiny-grid-column field="sort" :title="$t('global.table.columns.sort')" align="center" width="80"
              :editor="{ component: 'input' }" />
            <tiny-grid-column field="remark" show-overflow :title="$t('global.table.columns.remark')"
              :editor="{ component: 'input' }" />

            <tiny-grid-column :title="$t('global.table.operations')" align="center" width="120">
              <template #default="scope">
                <template v-if="gridTableRef && gridTableRef.hasActiveRow(scope.row)">
                  <tiny-action-menu :max-show-num="3" :spacing="8" :options="saveOptions"
                    @item-click="(data: any) => saveOptionsClick(data.itemData.label, scope.row)">
                    <template #item="{ data }">
                      <span> {{ $t(data.label) }} </span>
                    </template>
                  </tiny-action-menu>
                </template>
                <template v-else>
                  <tiny-action-menu :max-show-num="3" :spacing="8" :options="options"
                    @item-click="(data: any) => optionsClick(data.itemData.label, scope.row)">
                    <template #item="{ data }">
                      <span v-if="data.label == 'global.table.operations.delete'"
                        style="color: var(--button-delete-color);"> {{ $t(data.label) }} </span>
                      <span v-else> {{ $t(data.label) }} </span>
                    </template>
                  </tiny-action-menu>
                </template>
              </template>
            </tiny-grid-column>
          </tiny-grid>
        </div>
      </div>
    </div>
  </tiny-drawer>
</template>

<script lang="ts" setup>
import SystemRequest from '@/api/system/index'

const { proxy } = getCurrentInstance() as any
const emit = defineEmits(['ok'])

const visible = ref(false)
const title = computed(() => {
  return '按钮列表'
})

const menuId = ref<string>()

const gridTableRef = ref();
const loading = ref(false)
const tableData = ref<SystemPermissionAPI.ButtonVO[]>([])

async function getAllData(params: SystemPermissionAPI.ButtonAllQuery = {
  menuId: menuId.value
}) {
  const queryParmas: SystemPermissionAPI.ButtonAllQuery = {
    ...params,
  };
  loading.value = true;
  try {
    const { data } = await SystemRequest.button.queryButtonAll(queryParmas);
    tableData.value = data
  } finally {
    loading.value = false
  }
}

const options = ref([
  {
    label: 'global.table.operations.edit'
  },
  {
    label: 'global.table.operations.delete'
  }
])

const saveOptions = ref([
  {
    label: 'global.table.operations.save'
  },
  {
    label: 'global.table.operations.cancel'
  }
])

const optionsClick = (label: string, data: SystemPermissionAPI.ButtonVO) => {
  switch (label) {
    case 'global.table.operations.edit': {
      gridTableRef?.value.setActiveRow(data);
      break
    }
    case 'global.table.operations.delete': {
      handleDelete(data)
      break
    }
    default:
      console.log("code is error.")
  }
}

const saveOptionsClick = (label: string, data: SystemPermissionAPI.ButtonVO) => {
  switch (label) {
    case 'global.table.operations.save': {
      onSubmit(data)
      break
    }
    case 'global.table.operations.cancel': {
      gridTableRef?.value.clearActived()
      break
    }
    default:
      console.log("code is error.")
  }
}

const onSubmit = (data: SystemPermissionAPI.ButtonVO) => {
  if (data.id) {
    SystemRequest.button
      .updateButtonById(data.id, toRaw(data))
      .then((res) => {
        proxy.$modal.message({ message: '修改成功', status: 'success' });
        gridTableRef?.value.clearActived()
      })
      .catch((err) => { console.log(err) })
  } else {
    data.menuId = menuId.value || ''
    SystemRequest.button
      .addButton(toRaw(data))
      .then((res) => {
        proxy.$modal.message({ message: '创建成功', status: 'success' });
        getAllData()
      })
      .catch((err) => { console.log(err) })
  }
}

const handleDelete = (data: SystemPermissionAPI.ButtonVO) => {
  proxy.$modal.confirm({ message: `确定要删除按钮【${data.name}】吗?`, maskClosable: true, title: '删除提示' }).then((res: string) => {
    if (data.id && res === 'confirm') {
      SystemRequest.button.deleteButtonById(data.id).then(() => {
        getAllData()
        proxy.$modal.message({ message: '删除成功', status: 'success', });
      })
    }
  })
}

const toolbarButtons = reactive([
  {
    code: 'insert',
    name: '新增',
  }
])

const toolbarButtonClickEvent = ({ code, $grid }: any) => {
  switch (code) {
    case 'insert': {
      proxy.$refs.gridTableRef.insert({}).then((res: any) => {
        proxy.$refs.gridTableRef.setActiveRow(res.row)
      })
      break
    }
    default:
      console.log("code is error.")
  }
}

const onClose = () => {
  visible.value = false
}

const open = (mId: string) => {
  visible.value = true
  menuId.value = mId
  getAllData()
}

defineExpose({
  open
})
</script>