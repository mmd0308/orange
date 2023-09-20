<template>
  <div class="container-list">
    <tiny-form :model="filterOptions" label-position="right" label-width="100px" class="filter-form" size="small">
      <tiny-row :flex="true" justify="center">
        <tiny-col :span="4" label-width="100px">
          <tiny-form-item :label="$t('system.role.form.name')">
            <tiny-input v-model="filterOptions.account"
              :placeholder="$t('system.role.form.name.placeholder')"></tiny-input>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="4" label-width="100px">
          <div class="search-btn">
            <tiny-button type="primary" @click="handleFormQuery">
              {{ $t('global.form.search') }}
            </tiny-button>
            <tiny-button @click="handleFormReset">
              {{ $t('global.form.reset') }}
            </tiny-button>
          </div>
        </tiny-col>
      </tiny-row>
    </tiny-form>
    <div class="table-scroll">
      <div class="table-wrapper">
        <tiny-grid ref="gridTableRef" :fetch-data="fetchTableData" :pager="pagerConfig" :loading="loading"
          :auto-resize="true" @toolbar-button-click="toolbarButtonClickEvent">
          <template #toolbar>
            <tiny-grid-toolbar :buttons="toolbarButtons" refresh full-screen :setting="{ simple: true }" />
          </template>

          <tiny-grid-column field="traceId" :title="$t('system.record.login.table.columns.traceId')" align="center"
            width="150" />
          <tiny-grid-column field="account" :title="$t('system.record.login.table.columns.account')" align="center" />
          <tiny-grid-column field="userId" :title="$t('system.record.login.table.columns.userId')" align="center" />

          <tiny-grid-column field="status" :title="$t('global.table.columns.status')" align="center" />
          <tiny-grid-column field="createdAt" :title="$t('global.table.columns.createdAt')" align="center" width="135" />

          <tiny-grid-column :title="$t('global.table.operations')" align="center" width="100">
            <template #default="data">
              <tiny-button type="text" @click="handleDetail(data.row.id)"> {{
                $t('global.table.operations.detail')
              }}</tiny-button>
            </template>
          </tiny-grid-column>
        </tiny-grid>
      </div>
    </div>
  </div>

  <detail ref="detailsRef"></detail>
</template>

<script lang="ts" setup>
import {
  Grid as TinyGrid, GridColumn as TinyGridColumn, GridToolbar as TinyGridToolbar,
  Form as TinyForm, FormItem as TinyFormItem,
  Input as TinyInput, Button as TinyButton,
  Row as TinyRow, Col as TinyCol, Pager as TinyPager
} from '@opentiny/vue';

import SystemRequest from '@/api/system/index'
import detail from './components/detail.vue';

const detailsRef = ref();

const state = reactive<{
  loading: boolean;
  filterOptions: SystemRecordAPI.RecordLoginPageQuery;
}>({
  loading: false,
  filterOptions: {} as SystemRecordAPI.RecordLoginPageQuery,
});

const pagerConfig = reactive({
  component: TinyPager,
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

async function getPageData(params: SystemRecordAPI.RecordLoginPageQuery = {
  pageNo: 1,
  pageSize: 10
}) {
  const queryParmas: SystemRecordAPI.RecordLoginPageQuery = {
    ...filterOptions.value,
    ...params,
  };
  state.loading = true;
  try {
    const { data } = await SystemRequest.recordLogin.queryRecordLoginPage(queryParmas);
    const { records, total } = data;
    return {
      result: records,
      page: { total },
    };
  } finally {
    state.loading = false
  }
}

const handleDetail = (id: string) => {
  detailsRef.value.open(id)
}


const handleFormQuery = () => {
  gridTableRef?.value.handleFetch('reload');
}
const handleFormReset = () => {
  state.filterOptions = {} as SystemRecordAPI.RecordLoginPageQuery;
  handleFormQuery();
}

const toolbarButtons = reactive([
  {
    code: 'batchDelete',
    name: '批量删除'
  }
])

const toolbarButtonClickEvent = ({ code }: any) => {
  switch (code) {
    case 'batchDelete': {
      // editFormRef.value.open()
      break
    }
    default:
      console.log("code is error.")
  }
}

</script>

<style scoped lang="less"></style>