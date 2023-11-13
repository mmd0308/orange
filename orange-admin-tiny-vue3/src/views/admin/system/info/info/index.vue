<template>
  <div class="container-list">
    <tiny-form :model="filterOptions" label-position="right" label-width="100px" class="filter-form">
      <tiny-row :flex="true" justify="center">
        <tiny-col :span="4">
          <tiny-form-item :label="$t('system.info.form.title')">
            <tiny-input v-model="filterOptions.titleLike" clearable
              :placeholder="$t('system.info.form.title.placeholder')"></tiny-input>
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
          <tiny-grid-column field="title" :title="$t('system.info.table.columns.title')">
            <template #default="data">
              <tiny-link :underline="false" type="primary" @click="toDetail(data.row)"> {{ data.row.title }} </tiny-link>
            </template>
          </tiny-grid-column>
          <tiny-grid-column field="typeName" :title="$t('system.info.table.columns.type')" align="center" width="100" />
          <tiny-grid-column field="sort" :title="$t('global.table.columns.sort')" align="center" width="80" />
          <tiny-grid-column field="createdAt" :title="$t('global.table.columns.createdAt')" align="center" width="150" />
          <tiny-grid-column field="updatedAt" :title="$t('global.table.columns.updatedAt')" align="center" width="150" />
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

  <detail ref="detailRef"></detail>
  <editform ref="editFormRef" @ok="handleFormQuery"></editform>
</template>

<script lang="ts" setup>
import SystemRequest from '@/api/system/index'
import editform from './components/edit-form.vue';
import detail from './components/detail.vue';

const { proxy } = getCurrentInstance() as any
const editFormRef = ref();


const state = reactive<{
  loading: boolean;
  filterOptions: SystemInfoAPI.InfoPageQuery;
}>({
  loading: false,
  filterOptions: {} as SystemInfoAPI.InfoPageQuery,
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

async function getPageData(params: SystemInfoAPI.InfoPageQuery = {
  pageNo: 1,
  pageSize: 10
}) {
  const queryParmas: SystemInfoAPI.InfoPageQuery = {
    ...filterOptions.value,
    ...params,
  };
  state.loading = true;
  try {
    const { data } = await SystemRequest.info.queryInfoPage(queryParmas);
    const { records, total } = data;
    return {
      result: records,
      page: { total },
    };
  } finally {
    state.loading = false
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


const optionsClick = (label: string, data: SystemInfoAPI.InfoVO) => {
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

const handleDelete = (data: SystemInfoAPI.InfoVO) => {
  proxy.$modal.confirm({ message: `确定要删除文档【${data.title}】吗?`, maskClosable: true, title: '删除提示' }).then((res: string) => {
    if (data.id && res === 'confirm') {
      SystemRequest.info.deleteInfoById(data.id).then(() => {
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
  state.filterOptions = {} as SystemInfoAPI.InfoPageQuery;
  handleFormQuery();
}

const toolbarButtons = reactive([
  {
    code: 'insert',
    name: '新增',
  },
  {
    code: 'batchDelete',
    name: '批量删除'
  }
])

const toolbarButtonClickEvent = ({ code, $grid }: any) => {
  const data = $grid.getSelectRecords()
  switch (code) {
    case 'insert': {
      editFormRef.value.open()
      break
    }
    case 'batchDelete': {
      handleBatchDelete(data)
      break
    }
    default:
      console.log("code is error.")
  }
}

const handleBatchDelete = (data: SystemInfoAPI.InfoVO[]) => {
  let ids: string[] = data.map(item => item.id) as string[]
  if (ids.length === 0) {
    proxy.$modal.message({ message: '请选择需要删除的信息', status: 'warning' });
    return
  }
  proxy.$modal.confirm({ message: `确定要批量删除信息吗?`, maskClosable: true, title: '删除提示' }).then((res: string) => {
    if (res === 'confirm') {
      SystemRequest.info.deleteInfoByIds(ids).then(() => {
        handleFormQuery()
        proxy.$modal.message({ message: '批量删除成功', status: 'success' });
      })
    }
  })
}

const detailRef = ref()
const toDetail = (data: SystemInfoAPI.InfoVO) => {
  detailRef.value.open(data.id)
}

</script>

<style scoped lang="less"></style>