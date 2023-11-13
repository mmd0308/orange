<template>
  <div class="container-list">
    <tiny-form :model="filterOptions" label-position="right" label-width="100px" class="filter-form">
      <tiny-row :flex="true" justify="center">
        <tiny-col :span="4">
          <tiny-form-item :label="$t('system.dict-type.form.name')">
            <tiny-input v-model="filterOptions.nameLike" clearable
              :placeholder="$t('system.dict-type.form.name.placeholder')"></tiny-input>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="4">
          <tiny-form-item :label="$t('system.dict-type.form.dictType')">
            <tiny-input v-model="filterOptions.dictType" clearable
              :placeholder="$t('system.dict-type.form.dictType.placeholder')"></tiny-input>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="4">
          <tiny-button type="primary" @click="handleFormQuery">
            {{ $t('global.form.search') }}
          </tiny-button>
          <tiny-button @click="handleFormReset">
            {{ $t('global.form.reset') }}
          </tiny-button>
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
          <tiny-grid-column field="name" :title="$t('system.dict-type.table.columns.name')" />
          <tiny-grid-column field="dictType" :title="$t('system.dict-type.table.columns.dictType')" />
          <tiny-grid-column field="status" :title="$t('global.table.columns.status')" align="center" width="80">
            <template #default="scope">
              <dict-tag :value="scope.row.status" :options="proxy.$dict.getDict('sys_common_data_enable_status')" />
            </template>
          </tiny-grid-column>
          <tiny-grid-column field="presetFlag" :title="$t('global.table.columns.presetFlag')" align="center" width="80">
            <template #default="scope">
              <dict-tag :value="scope.row.presetFlag" :options="proxy.$dict.getDict('sys_common_data_preset_flag')" />
            </template>
          </tiny-grid-column>
          <tiny-grid-column field="remark" show-overflow :title="$t('global.table.columns.remark')" width="260" />
          <tiny-grid-column field="updatedAt" :title="$t('global.table.columns.updatedAt')" align="center" width="150" />

          <tiny-grid-column :title="$t('global.table.operations')" align="center" width="165">
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
  filterOptions: SystemDictAPI.DictTypePageQuery;
}>({
  loading: false,
  filterOptions: {} as SystemDictAPI.DictTypePageQuery,
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

async function getPageData(params: SystemDictAPI.DictTypePageQuery = {
  pageNo: 1,
  pageSize: 10
}) {
  const queryParmas: SystemDictAPI.DictTypePageQuery = {
    ...filterOptions.value,
    ...params,
  };
  state.loading = true;
  try {
    const { data } = await SystemRequest.dictType.queryDictTypePage(queryParmas);
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
    label: 'system.dict-type.operations.dataList'
  },
  {
    label: 'global.table.operations.edit'
  },
  {
    label: 'global.table.operations.delete'
  }
])

const editFormRef = ref();

const optionsClick = (label: string, data: SystemDictAPI.DictTypeVO) => {
  switch (label) {
    case 'system.dict-type.operations.dataList': {
      proxy.$router.push({
        path: `${import.meta.env.VITE_CONTEXT}system/permission/dict-data`,
        query: { dictType: data.dictType }
      });
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

const handleDelete = (data: SystemDictAPI.DictTypeVO) => {
  proxy.$modal.confirm({ message: `确定要删除字典类型【${data.name}】吗?`, maskClosable: true, title: '删除提示' })
    .then((res: string) => {
      if (data.id && res === 'confirm') {
        SystemRequest.dictType.deleteDictTypeById(data.id).then(() => {
          handleFormQuery()
          proxy.$modal.message({ message: '删除成功', status: 'success' });
        })
      }
    })
}

const handleFormQuery = () => {
  gridTableRef?.value.handleFetch('reload');
}
const handleFormReset = () => {
  state.filterOptions = {} as SystemDictAPI.DictTypePageQuery;
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
      proxy.$modal.message("开发中...")
      break
    }
    default:
      console.log("code is error.")
  }
}

</script>

<style scoped lang="less"></style>