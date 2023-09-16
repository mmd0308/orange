<template>
  <div class="container-list">
    <tiny-form :model="filterOptions" label-position="right" label-width="100px" class="filter-form" size="small">
      <tiny-row :flex="true" justify="center">
        <tiny-col :span="4" label-width="100px">
          <tiny-form-item :label="$t('system.user.form.name')">
            <tiny-input v-model="filterOptions.nameLike" clearable
              :placeholder="$t('system.user.form.name.placeholder')"></tiny-input>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="4" label-width="100px">
          <tiny-form-item :label="$t('system.user.form.username')" prop="id">
            <tiny-input v-model="filterOptions.usernameLike" clearable
              :placeholder="$t('system.user.form.username.placeholder')"></tiny-input>
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
          <tiny-grid-column field="avatar" :title="$t('system.user.table.columns.avatar')" align="center" />
          <tiny-grid-column field="name" :title="$t('system.user.table.columns.name')" align="center" />
          <tiny-grid-column field="username" :title="$t('system.user.table.columns.username')" align="center" />
          <tiny-grid-column field="email" :title="$t('system.user.table.columns.email')" align="center" />
          <tiny-grid-column field="sex" :title="$t('system.user.table.columns.sex')" align="center">
            <template #default="data">
              <dict-tag :value="data.row.sex" :options="proxy.$dict.getDict('sys_common_user_sex')" />
            </template>
          </tiny-grid-column>
          <tiny-grid-column field="phone" :title="$t('system.user.table.columns.phone')" align="center" />
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

const { proxy } = getCurrentInstance() as any

const editFormRef = ref();

const state = reactive<{
  loading: boolean;
  filterOptions: SystemPermissionAPI.UserPageQuery;
}>({
  loading: false,
  filterOptions: {} as SystemPermissionAPI.UserPageQuery,
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

async function getPageData(params: SystemPermissionAPI.UserPageQuery = {
  pageNo: 1,
  pageSize: 10
}) {
  const queryParmas: SystemPermissionAPI.UserPageQuery = {
    ...filterOptions.value,
    ...params,
  };
  state.loading = true;
  try {
    const { data } = await SystemRequest.user.queryUserPage(queryParmas);
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
  SystemRequest.user.deleteUserById(id).then((res) => {
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
  state.filterOptions = {} as SystemPermissionAPI.UserPageQuery;
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

const toolbarButtonClickEvent = ({ code }: any) => {
  switch (code) {
    case 'insert': {
      editFormRef.value.open()
      break
    }
    case 'batchDelete': {
      editFormRef.value.open()
      break
    }
    default:
      console.log("code is error.")
  }
}

</script>

<style scoped lang="less"></style>