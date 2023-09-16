<template>
  <div class="container-list">
    <tiny-form :model="filterOptions" label-position="right" label-width="100px" class="filter-form" size="small">
      <tiny-row :flex="true" justify="center" class="col">
        <tiny-col :span="4" label-width="100px">
          <tiny-form-item :label="$t('system.role.form.name')">
            <tiny-input v-model="filterOptions.nameLike" clearable
              :placeholder="$t('system.role.form.name.placeholder')"></tiny-input>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="4" label-width="100px">
          <tiny-form-item :label="$t('system.role.form.permission')">
            <tiny-input v-model="filterOptions.permissionLike" clearable
              :placeholder="$t('system.role.form.permission.placeholder')"></tiny-input>
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
            <tiny-grid-toolbar :buttons="toolbarButtons" refresh full-screen :setting="{ simple: true }" />
          </template>
          <tiny-grid-column type="selection" width="50"></tiny-grid-column>
          <tiny-grid-column field="name" :title="$t('system.role.table.columns.name')" align="center" />
          <tiny-grid-column field="permission" :title="$t('system.role.table.columns.permission')" align="center" />
          <tiny-grid-column field="status" :title="$t('global.table.columns.status')" align="center" />
          <tiny-grid-column field="sort" :title="$t('global.table.columns.sort')" align="center" />
          <tiny-grid-column field="createdAt" :title="$t('global.table.columns.createdAt')" align="center" />

          <tiny-grid-column :title="$t('global.table.operations')" align="center">
            <template #default="data">
              <tiny-button type="text" @click="handleEdit(data.row.id)"> {{
                $t('global.table.operations.edit')
              }}</tiny-button>
              <tiny-popconfirm :title="`确定要删除角色【${data.row.name}】吗?`" type="warning" trigger="click"
                @confirm="handleDelete(data.row.id)">
                <template #reference>
                  <tiny-button type="text" class="table-delete-button"> {{
                    $t('global.table.operations.delete')
                  }}</tiny-button>
                </template>
              </tiny-popconfirm>
            </template>
          </tiny-grid-column>
        </tiny-grid>
      </div>
    </div>
  </div>

  <editform ref="editFormRef" @ok="handleFormQuery"></editform>
</template>

<script lang="ts" setup>
import {
  Grid as TinyGrid, GridColumn as TinyGridColumn, GridToolbar as TinyGridToolbar,
  Form as TinyForm, FormItem as TinyFormItem,
  Input as TinyInput, Button as TinyButton,
  Row as TinyRow, Col as TinyCol, Pager as TinyPager,
  Modal, Popconfirm as TinyPopconfirm
} from '@opentiny/vue';

import SystemRequest from '@/api/system/index'

import editform from './components/edit-form.vue';

const editFormRef = ref();

const state = reactive<{
  loading: boolean;
  filterOptions: SystemPermissionAPI.RolePageQuery;
}>({
  loading: false,
  filterOptions: {} as SystemPermissionAPI.RolePageQuery,
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

async function getPageData(params: SystemPermissionAPI.RolePageQuery = {
  pageNo: 1,
  pageSize: 10
}) {
  const queryParmas: SystemPermissionAPI.RolePageQuery = {
    ...filterOptions.value,
    ...params,
  };
  state.loading = true;
  try {
    const { data } = await SystemRequest.role.queryRolePage(queryParmas);
    const { records, total } = data;
    return {
      result: records,
      page: { total },
    };
  } finally {
    state.loading = false
  }
}

const handleEdit = (id: string) => {
  editFormRef.value.open(id)
}

const handleDelete = (id: string) => {
  SystemRequest.role.deleteRoleById(id).then((res) => {
    getPageData()
    Modal.message({
      message: '删除成功',
      status: 'success',
    });
  })
}

const handleFormQuery = () => {
  gridTableRef?.value.handleFetch('reload');
}
const handleFormReset = () => {
  state.filterOptions = {} as SystemPermissionAPI.RolePageQuery;
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
      debugger
      break
    }
    default:
      console.log("code is error.")
  }
}

</script>

<style scoped lang="less"></style>