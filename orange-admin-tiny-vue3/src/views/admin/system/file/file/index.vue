<template>
  <div class="container-list">
    <tiny-form :model="filterOptions" label-position="right" label-width="100px" class="filter-form">
      <tiny-row :flex="true" justify="center">
        <tiny-col :span="4">
          <tiny-form-item :label="$t('system.file.form.name')">
            <tiny-input v-model="filterOptions.nameLike" clearable
              :placeholder="$t('system.file.form.name.placeholder')"></tiny-input>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="4">
          <tiny-form-item :label="$t('global.form.createdAt')">
            <tiny-date-picker v-model="createdTime" :default-time="['00:00:00', '23:59:59']" type="datetimerange"
              clearable value-format="yyyy-MM-dd HH:mm:ss" @change="handleChange"></tiny-date-picker>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="4">
          <div class="search-btn">
            <tiny-button type="primary" @click="handleFormQuery"> {{ $t('global.form.search') }} </tiny-button>
            <tiny-button @click="handleFormReset"> {{ $t('global.form.reset') }} </tiny-button>
          </div>
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
          <tiny-grid-column field="originalName" :title="$t('system.file.table.columns.originalName')" align="center" />
          <tiny-grid-column field="name" :title="$t('system.file.table.columns.name')" align="center" />
          <tiny-grid-column field="path" :title="$t('system.file.table.columns.path')" align="center" />
          <tiny-grid-column field="type" :title="$t('system.file.table.columns.type')" align="center" width="80" />
          <tiny-grid-column field="createdAt" :title="$t('global.table.columns.createdAt')" align="center" width="150" />

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
const editFormRef = ref();

const state = reactive<{
  loading: boolean;
  filterOptions: SystemFileAPI.FilePageQuery;
}>({
  loading: false,
  filterOptions: {} as SystemFileAPI.FilePageQuery,
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

async function getPageData(params: SystemFileAPI.FilePageQuery = {
  pageNo: 1,
  pageSize: 10
}) {
  const queryParmas: SystemFileAPI.FilePageQuery = {
    ...filterOptions.value,
    ...params,
  };
  state.loading = true;
  try {
    const { data } = await SystemRequest.file.queryFilePage(queryParmas);
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


const optionsClick = (label: string, data: SystemFileAPI.FileVO) => {
  switch (label) {
    case 'global.table.operations.edit': {
      proxy.$modal.message('开发中...');
      // editFormRef.value.open(data.id)
      break
    }
    case 'global.table.operations.delete': {
      proxy.$modal.message('开发中...');
      // handleDelete(data)
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

const createdTime = ref();
const handleChange = (time: [string, string]) => {
  if (time) {
    let [startTime, endTime] = time
    filterOptions.value.createdStartTime = startTime
    filterOptions.value.createdEndTime = endTime
  } else {
    filterOptions.value.createdStartTime = undefined
    filterOptions.value.createdEndTime = undefined
  }

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
      proxy.$modal.message('开发中...');
      // editFormRef.value.open()
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