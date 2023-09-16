<template>
  <div class="container-list">
    <tiny-form :model="filterOptions" label-position="right" label-width="100px" class="filter-form" size="small">
      <tiny-row :flex="true" justify="center" class="col">
        <tiny-col :span="4" label-width="100px">
          <tiny-form-item :label="$t('system.record.operation.form.traceId')">
            <tiny-input v-model="filterOptions.traceId" clearable
              :placeholder="$t('system.record.operation.form.traceId.placeholder')"></tiny-input>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="4" label-width="100px">
          <tiny-form-item :label="$t('system.record.operation.form.operationTime')">
            <tiny-date-picker v-model="operationTime" :default-time="['00:00:00', '23:59:59']" type="datetimerange"
              clearable value-format="yyyy-MM-dd HH:mm:ss" @change="handleChange"></tiny-date-picker>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="4" label-width="100px">
          <tiny-form-item>
            <div class="search-btn">
              <tiny-button type="primary" @click="handleFormQuery">
                {{ $t('global.form.search') }}
              </tiny-button>
              <tiny-button @click="handleFormReset">
                {{ $t('global.form.reset') }}
              </tiny-button>
            </div>
          </tiny-form-item>
        </tiny-col>
      </tiny-row>
    </tiny-form>
    <div class="tiny-fullscreen-scroll">
      <div class="tiny-fullscreen-wrapper">
        <tiny-grid ref="gridTableRef" :fetch-data="fetchTableData" :pager="pagerConfig" :loading="loading"
          :auto-resize="true" @toolbar-button-click="toolbarButtonClickEvent">
          <template #toolbar>
            <tiny-grid-toolbar :buttons="toolbarButtons" full-screen :setting="{ simple: true }" refresh />
          </template>
          <tiny-grid-column field="traceId" :title="$t('system.record.operation.table.columns.traceId')" align="center" />
          <tiny-grid-column field="resourceId" :title="$t('system.record.operation.table.columns.resourceId')"
            align="center" width="200" />
          <tiny-grid-column field="requestMethod" :title="$t('system.record.operation.table.columns.requestMethod')"
            align="center" />
          <tiny-grid-column field="userIp" :title="$t('system.record.operation.table.columns.userIp')" align="center" />
          <tiny-grid-column field="userId" :title="$t('system.record.operation.table.columns.userId')" align="center" />
          <tiny-grid-column field="status" :title="$t('system.record.operation.table.columns.status')" align="center">
            <template #default="data">
              <dict-tag :value="data.row.status" :options="proxy.$dict.getDict('sys_common_operation_status')" />
            </template>
          </tiny-grid-column>
          <tiny-grid-column field="startTime" :title="$t('system.record.operation.table.columns.startTime')"
            align="center" />
          <tiny-grid-column field="executeTime" :title="$t('system.record.operation.table.columns.executeTime')"
            align="center">
            <template #default="data">
              {{ data.row.executeTime }} ms</template>
          </tiny-grid-column>

          <tiny-grid-column :title="$t('global.table.operations')" align="center">
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
  Row as TinyRow, Col as TinyCol, Pager as TinyPager,
  Modal, DatePicker as TinyDatePicker
} from '@opentiny/vue';

import SystemRequest from '@/api/system/index'

import detail from './components/detail.vue';

const { proxy } = getCurrentInstance() as any
const detailsRef = ref();

const state = reactive<{
  loading: boolean;
  filterOptions: SystemRecordAPI.RecordOperationPageQuery;
}>({
  loading: false,
  filterOptions: {} as SystemRecordAPI.RecordOperationPageQuery,
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

async function getPageData(params: SystemRecordAPI.RecordOperationPageQuery = {
  pageNo: 1,
  pageSize: 10
}) {
  const queryParmas: SystemRecordAPI.RecordOperationPageQuery = {
    ...filterOptions.value,
    ...params,
  };
  state.loading = true;
  try {
    const { data } = await SystemRequest.recordOperation.queryRecordOperationPage(queryParmas);
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

const operationTime = ref();
const handleChange = (time: [string, string]) => {
  if (time) {
    let [startTime, endTime] = time
    filterOptions.value.operationStartTime = startTime
    filterOptions.value.operationEndTime = endTime
  } else {
    filterOptions.value.operationStartTime = undefined
    filterOptions.value.operationEndTime = undefined
  }

}

const handleFormQuery = () => {
  gridTableRef?.value.handleFetch('reload');
}
const handleFormReset = () => {
  state.filterOptions = {} as SystemRecordAPI.RecordOperationPageQuery;
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
      detailsRef.value.open()
      break
    }
    default:
      console.log("code is error.")
  }
}

</script>

<style scoped lang="less"></style>