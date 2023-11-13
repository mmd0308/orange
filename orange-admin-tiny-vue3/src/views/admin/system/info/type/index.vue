<template>
  <div class="container-list">
    <tiny-form :model="filterOptions" label-position="right" label-width="100px" class="filter-form">
      <tiny-row :flex="true" justify="center">
        <tiny-col :span="4">
          <tiny-form-item :label="$t('system.info.type.form.name')">
            <tiny-input v-model="filterOptions.nameLike" clearable
              :placeholder="$t('system.info.type.form.name.placeholder')"></tiny-input>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="8">
          <tiny-button type="primary" @click="handleFormQuery"> {{ $t('global.form.search') }} </tiny-button>
          <tiny-button @click="handleFormReset"> {{ $t('global.form.reset') }} </tiny-button>
        </tiny-col>
      </tiny-row>
    </tiny-form>
    <div class="table-scroll">
      <div class="table-wrapper">
        <tiny-grid ref="gridTableRef" max-height="580px" :data="tableData" :loading="loading"
          :tree-config="{ children: 'children' }" :auto-resize="true" @toolbar-button-click="toolbarButtonClickEvent">
          <template #toolbar>
            <tiny-grid-toolbar :buttons="toolbarButtons" full-screen :setting="{ simple: true }" />
          </template>
          <tiny-grid-column field="index" width="50" tree-node></tiny-grid-column>
          <tiny-grid-column field="name" :title="$t('system.info.type.table.columns.name')" />
          <tiny-grid-column field="code" :title="$t('system.info.type.table.columns.code')" align="center" width="120" />
          <tiny-grid-column field="presetFlag" :title="$t('global.table.columns.presetFlag')" align="center" width="80">
            <template #default="data">
              <dict-tag :value="data.row.presetFlag" :options="proxy.$dict.getDict('sys_common_data_preset_flag')" />
            </template>
          </tiny-grid-column>

          <tiny-grid-column field="sort" :title="$t('global.table.columns.sort')" align="center" width="80" />
          <tiny-grid-column field="remark" show-overflow :title="$t('global.table.columns.remark')" width="260" />
          <tiny-grid-column field="createdAt" :title="$t('global.table.columns.createdAt')" width="150" />

          <tiny-grid-column :title="$t('global.table.operations')" align="center" width="100">
            <template #default="scope">
              <tiny-action-menu :max-show-num="3" :spacing="8" :options="options"
                @item-click="(data: any) => optionsClick(data.itemData.label, scope.row)">
                <template #item="{ data }">
                  <span v-if="data.label == 'global.table.operations.delete'" style="color: var(--button-delete-color);">
                    {{ $t(data.label) }} </span>
                  <span v-else> {{ $t(data.label) }} </span>
                </template>
              </tiny-action-menu>
            </template>
          </tiny-grid-column>
        </tiny-grid>
      </div>
    </div>
  </div>

  <editform ref="editFormRef" @ok="handleFormQuery"></editform>
</template>

<script lang="ts" setup>
import SystemRequest from '@/api/system/index'
import editform from './components/edit-form.vue';

const { proxy } = getCurrentInstance() as any
const state = reactive<{
  loading: boolean;
  filterOptions: SystemInfoAPI.InfoTypeAllQuery;
}>({
  loading: false,
  filterOptions: {} as SystemInfoAPI.InfoTypeAllQuery,
});


const gridTableRef = ref();
const { loading, filterOptions } = toRefs(state);

const tableData = ref<SystemInfoAPI.InfoTypeTreeVO[]>([])

async function getAllData() {
  const queryParmas: SystemInfoAPI.InfoTypeAllQuery = {
    ...filterOptions.value,
  };
  state.loading = true;
  try {
    const { data } = await SystemRequest.infoType.queryInfoTypeAll(queryParmas);
    tableData.value = aggregateTableData(data)
  } finally {
    state.loading = false
  }
}
getAllData()

const options = ref([
  {
    label: 'global.table.operations.edit'
  },
  {
    label: 'global.table.operations.delete'
  }
])

const editFormRef = ref();

const optionsClick = (label: string, data: SystemInfoAPI.InfoTypeVO) => {
  switch (label) {
    case 'global.table.operations.edit': {
      editFormRef.value.open(data.id)
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

const handleDelete = (data: SystemInfoAPI.InfoTypeVO) => {
  proxy.$modal.confirm({ message: `确定要删除类型【${data.name}】吗?`, maskClosable: true, title: '删除提示' }).then((res: string) => {
    if (data.id && res === 'confirm') {
      SystemRequest.infoType.deleteInfoTypeById(data.id).then(() => {
        getAllData()
        proxy.$modal.message({ message: '删除成功', status: 'success', });
      })
    }
  })
}

const handleFormQuery = () => {
  getAllData()
}
const handleFormReset = () => {
  state.filterOptions = {} as SystemInfoAPI.InfoTypeAllQuery;
  handleFormQuery();
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

const toolbarButtons = reactive([
  {
    code: 'insert',
    name: '新增',
  }
])

const toolbarButtonClickEvent = ({ code }: any) => {
  switch (code) {
    case 'insert': {
      editFormRef.value.open()
      break
    }
    default:
      console.log("code is error.")
  }
}

</script>

<style scoped lang="less"></style>