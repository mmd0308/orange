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
          <tiny-grid-column type="selection" width="50"></tiny-grid-column>
          <tiny-grid-column field="avatar" :title="$t('system.user.table.columns.avatar')" align="center" width="100">
            <tiny-user-head type="icon" round min></tiny-user-head>
          </tiny-grid-column>
          <tiny-grid-column field="name" :title="$t('system.user.table.columns.name')" align="center" />
          <tiny-grid-column field="username" :title="$t('system.user.table.columns.username')" align="center" />
          <tiny-grid-column field="email" :title="$t('system.user.table.columns.email')" align="center" />
          <tiny-grid-column field="sex" :title="$t('system.user.table.columns.sex')" align="center">
            <template #default="data">
              <dict-tag :value="data.row.sex" :options="proxy.$dict.getDict('sys_common_user_sex')" />
            </template>
          </tiny-grid-column>
          <tiny-grid-column field="phone" :title="$t('system.user.table.columns.phone')" align="center" />
          <tiny-grid-column :title="$t('global.table.operations')" align="center" width="180">
            <template #default="scope">
              <tiny-action-menu :max-show-num="2" :spacing="8" :options="options"
                @item-click="(data: any) => optionsClick(data.itemData.label, scope.row)">
                <template #item="{ data }">
                  <span> {{ $t(data.label) }}</span>
                </template>
              </tiny-action-menu>
            </template>
          </tiny-grid-column>
        </tiny-grid>
      </div>
    </div>
  </div>

  <editform ref="editFormRef" @ok="handleFormQuery"></editform>
  <allot-role ref="allotRoleRef"></allot-role>
  <reset-password ref="resetPasswordRef"></reset-password>
</template>

<script lang="ts" setup>
import {
  Grid as TinyGrid, GridColumn as TinyGridColumn, GridToolbar as TinyGridToolbar,
  Form as TinyForm, FormItem as TinyFormItem,
  Input as TinyInput, Button as TinyButton,
  Row as TinyRow, Col as TinyCol, Pager as TinyPager,
  Modal, ActionMenu as TinyActionMenu, UserHead as TinyUserHead
} from '@opentiny/vue';

import SystemRequest from '@/api/system/index'

import editform from './components/edit-form.vue';
import allotRole from './components/allot-role.vue'
import resetPassword from './components/reset-password.vue';

const { proxy } = getCurrentInstance() as any

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

const options = ref([
  {
    label: 'global.table.operations.edit'
  },
  {
    label: 'global.table.operations.allotUserRole'
  },
  {
    label: 'global.table.operations.resetPassword'
  },
  {
    label: 'global.table.operations.delete'
  }
])

const editFormRef = ref();
const allotRoleRef = ref();
const resetPasswordRef = ref();

const optionsClick = (label: string, data: SystemPermissionAPI.UserVO) => {
  switch (label) {
    case 'global.table.operations.edit': {
      editFormRef.value.open(data.id)
      break
    }
    case 'global.table.operations.allotUserRole': {
      allotRoleRef.value.open(data.id)
      break
    }
    case 'global.table.operations.resetPassword': {
      resetPasswordRef.value.open(data.id)
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

const handleDelete = (data: SystemPermissionAPI.UserVO) => {
  Modal.confirm({ message: `确定要删除用户【${data.name}】吗?`, maskClosable: true, title: '删除提示' }).then((res: string) => {
    if (data.id && res === 'confirm') {
      SystemRequest.user.deleteUserById(data.id).then(() => {
        handleFormQuery()
        Modal.message({
          message: '删除成功',
          status: 'success',
        });
      })
    }
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

const handleBatchDelete = (data: SystemPermissionAPI.UserVO[]) => {
  let ids: string[] = data.map(item => item.id) as string[]
  if (ids.length === 0) {
    Modal.message({
      message: '请选择需要删除的用户',
      status: 'warning',
    });
    return
  }
  Modal.confirm({ message: `确定要批量删除用户吗?`, maskClosable: true, title: '删除提示' }).then((res: string) => {
    if (res === 'confirm') {
      SystemRequest.user.deleteUserByIds(ids).then(() => {
        handleFormQuery()
        Modal.message({
          message: '批量删除成功',
          status: 'success',
        });
      })
    }
  })
}

</script>

<style scoped lang="less"></style>