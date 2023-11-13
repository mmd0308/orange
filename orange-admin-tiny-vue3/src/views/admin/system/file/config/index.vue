<template>
  <div class="container-list">
    <tiny-form :model="filterOptions" label-position="right" label-width="100px" class="filter-form">
      <tiny-row :flex="true" justify="center">
        <tiny-col :span="4">
          <tiny-form-item :label="$t('system.file.config.form.name')">
            <tiny-input v-model="filterOptions.nameLike" clearable
              :placeholder="$t('system.file.config.form.name.placeholder')"></tiny-input>
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
        <tiny-grid ref="gridTableRef" max-height="580px" :fetch-data="fetchTableData" :pager="pagerConfig"
          :loading="loading" :auto-resize="true" @toolbar-button-click="toolbarButtonClickEvent">
          <template #toolbar>
            <tiny-grid-toolbar :buttons="toolbarButtons" refresh full-screen :setting="{ simple: true }" />
          </template>
          <tiny-grid-column type="selection" width="50"></tiny-grid-column>
          <tiny-grid-column field="name" :title="$t('system.file.config.table.columns.name')" align="center" />
          <tiny-grid-column field="storage" :title="$t('system.file.config.table.columns.storage')" align="center">
            <template #default="data">
              <template v-for="item in storages" :key="item.key">
                <span v-if="item.key == data.row.storage">{{ item.name }}</span>
              </template>
            </template>
          </tiny-grid-column>
          <tiny-grid-column field="master" :title="$t('system.file.config.table.columns.master')" align="center"
            width="80">
            <template #default="data">
              <tiny-tag v-if="data.row.master" type="success">是</tiny-tag>
              <tiny-tag v-else>否</tiny-tag>
            </template>
          </tiny-grid-column>

          <tiny-grid-column field="updatedAt" :title="$t('global.table.columns.updatedAt')" align="center" width="150" />
          <tiny-grid-column field="remark" show-overflow :title="$t('global.table.columns.remark')" width="260" />

          <tiny-grid-column :title="$t('global.table.operations')" align="center" width="170">
            <template #default="scope">
              <tiny-action-menu :max-show-num="3" :spacing="8"
                :options="options.filter((item) => !(item.label === 'system.table.operations.master' && scope.row.master))"
                @item-click="(data: any) => optionsClick(data.itemData.label, scope.row)">
                <template #item="{ data }">
                  <span v-if="data.label == 'global.table.operations.delete'" style="color: var(--button-delete-color);">
                    {{ $t(data.label) }}
                  </span>
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
const editFormRef = ref();


const state = reactive<{
  loading: boolean;
  filterOptions: SystemFileAPI.ConfigPageQuery;
}>({
  loading: false,
  filterOptions: {} as SystemFileAPI.ConfigPageQuery,
});

const pagerConfig = reactive({
  attrs: {
    currentPage: 1,
    pageSize: 10,
    pageSizes: [10, 20, 30, 50, 100],
    total: 0,
    align: 'right',
    layout: 'total, prev, pager, next, jumper, sizes',
  },
});

const gridTableRef = ref();
const { loading, filterOptions } = toRefs(state);

const fetchTableData = reactive({
  api: ({ page }: any) => {
    const { currentPage, pageSize } = page;
    return getPageData({
      pageNo: currentPage,
      pageSize,
    });
  }
});

async function getPageData(params: SystemFileAPI.ConfigPageQuery = {
  pageNo: 1,
  pageSize: 10
}) {
  const queryParmas: SystemFileAPI.ConfigPageQuery = {
    ...filterOptions.value,
    ...params,
  };
  state.loading = true;
  try {
    const { data } = await SystemRequest.fileConfig.queryConfigPage(queryParmas);
    const { records, total } = data;
    return {
      result: records,
      page: { total },
    };
  } finally {
    state.loading = false
  }
}

const storages = ref<SystemFileAPI.Storage[]>([])
const getStorages = async () => {
  const key = "file_config_storage"
  let storage = sessionStorage.getItem(key)
  if (!storage) {
    const { data } = await SystemRequest.fileConfig.queryAllStorages();
    sessionStorage.setItem(key, JSON.stringify(data))
    storage = data
  } else {
    storages.value = JSON.parse(storage)
  }
}
getStorages()

const options = ref([
  {
    label: 'system.table.operations.master'
  },
  {
    label: 'global.table.operations.edit'
  },
  {
    label: 'global.table.operations.delete'
  }
])

const optionsClick = (label: string, data: SystemFileAPI.ConfigVO) => {
  switch (label) {
    case 'system.table.operations.master': {
      if (data.id) {
        SystemRequest.fileConfig.updateMasterById(data.id)
          .then(res => {
            handleFormQuery()
          })
      }
      break
    }
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

const handleDelete = (data: SystemFileAPI.ConfigVO) => {
  proxy.$modal.confirm({ message: `确定要删除配置【${data.name}】吗?`, maskClosable: true, title: '删除提示' })
    .then((res: string) => {
      if (data.id && res === 'confirm') {
        SystemRequest.fileConfig.deleteConfigById(data.id).then(() => {
          handleFormQuery()
          proxy.$modal.message({ message: '删除成功', status: 'success', });
        })
      }
    })
}

const handleFormQuery = () => {
  gridTableRef?.value.handleFetch('reload');
}
const handleFormReset = () => {
  state.filterOptions = {} as SystemFileAPI.ConfigPageQuery;
  handleFormQuery();
}

const toolbarButtons = reactive([
  {
    code: 'insert',
    name: '新增',
  },
])

const toolbarButtonClickEvent = ({ code, $grid }: any) => {
  const data = $grid.getSelectRecords()
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