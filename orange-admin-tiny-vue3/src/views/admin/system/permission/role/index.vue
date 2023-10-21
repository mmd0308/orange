<template>
  <div class="container-list">
    <tiny-form :model="filterOptions" label-position="right" label-width="100px" class="filter-form">
      <tiny-row :flex="true" justify="center">
        <tiny-col :span="4">
          <tiny-form-item :label="$t('system.role.form.name')">
            <tiny-input v-model="filterOptions.nameLike" clearable
              :placeholder="$t('system.role.form.name.placeholder')"></tiny-input>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="4">
          <tiny-form-item :label="$t('system.role.form.permission')">
            <tiny-input v-model="filterOptions.permissionLike" clearable
              :placeholder="$t('system.role.form.permission.placeholder')"></tiny-input>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="4">
          <tiny-button type="primary" @click="handleFormQuery"> {{ $t('global.form.search') }} </tiny-button>
          <tiny-button @click="handleFormReset"> {{ $t('global.form.reset') }} </tiny-button>
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
          <tiny-grid-column field="name" :title="$t('system.role.table.columns.name')" align="center" />
          <tiny-grid-column field="permission" :title="$t('system.role.table.columns.permission')" align="center" />
          <tiny-grid-column field="status" :title="$t('global.table.columns.status')" align="center">
            <template #default="data">
              <dict-tag :value="data.row.status" :options="proxy.$dict.getDict('sys_common_data_status')" />
            </template>
          </tiny-grid-column>
          <tiny-grid-column field="sort" :title="$t('global.table.columns.sort')" align="center" />
          <tiny-grid-column field="createdAt" :title="$t('global.table.columns.createdAt')" align="center" width="150" />
          <tiny-grid-column field="remark" show-overflow :title="$t('global.table.columns.remark')" width="260" />

          <tiny-grid-column :title="$t('global.table.operations')" align="center" width="170">
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
  <allotResource ref="allotResourceRef"></allotResource>
</template>

<script lang="ts" setup>
import SystemRequest from '@/api/system/index'
import editform from './components/edit-form.vue';
import allotResource from './components/allot-resource.vue';

const { proxy } = getCurrentInstance() as any
const editFormRef = ref();
const allotResourceRef = ref();


const state = reactive<{
  loading: boolean;
  filterOptions: SystemPermissionAPI.RolePageQuery;
}>({
  loading: false,
  filterOptions: {} as SystemPermissionAPI.RolePageQuery,
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
const options = ref([
  {
    label: 'global.table.operations.edit'
  },
  {
    label: 'global.table.operations.menuPermission'
  },
  {
    label: 'global.table.operations.delete'
  }
])


const optionsClick = (label: string, data: SystemPermissionAPI.RoleVO) => {
  switch (label) {
    case 'global.table.operations.edit': {
      editFormRef.value.open(data.id)
      break
    }
    case 'global.table.operations.menuPermission': {
      allotResourceRef.value.open(data)
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

const handleDelete = (data: SystemPermissionAPI.RoleVO) => {
  proxy.$modal.confirm({ message: `确定要删除角色【${data.name}】吗?`, maskClosable: true, title: '删除提示' }).then((res: string) => {
    if (data.id && res === 'confirm') {
      SystemRequest.role.deleteRoleById(data.id).then(() => {
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
  state.filterOptions = {} as SystemPermissionAPI.RolePageQuery;
  handleFormQuery();
}

const toolbarButtons = reactive([
  {
    code: 'insert',
    name: '新增',
  },
  // {
  //   code: 'batchDelete',
  //   name: '批量删除'
  // }
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