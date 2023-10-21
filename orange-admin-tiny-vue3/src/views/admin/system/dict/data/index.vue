<template>
  <div class="container-list">
    <tiny-form :model="filterOptions" label-position="right" label-width="100px" class="filter-form">
      <tiny-row :flex="true" justify="center">
        <tiny-col :span="4">
          <tiny-form-item :label="$t('system.dict-data.form.dictType')">
            <tiny-select v-model="filterOptions.dictType" :placeholder="$t('system.dict-data.form.dictType.placeholder')"
              clearable>
              <tiny-option v-for="item in dictTypeOptions" :key="item.dictType" :label="item.name" :value="item.dictType">
              </tiny-option>
            </tiny-select>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="4">
          <tiny-form-item :label="$t('system.dict-data.form.dictLabel')">
            <tiny-input v-model="filterOptions.dictLabel" clearable
              :placeholder="$t('system.dict-data.form.dictLabel.placeholder')" />
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
            <tiny-grid-toolbar :buttons="toolbarButtons" refresh full-screen />
          </template>
          <tiny-grid-column type="selection" width="50"></tiny-grid-column>
          <tiny-grid-column field="dictLabel" :title="$t('system.dict-data.table.columns.dictLabel')" align="center" />
          <tiny-grid-column field="dictValue" :title="$t('system.dict-data.table.columns.dictValue')" align="center" />
          <tiny-grid-column field="dictType" :title="$t('system.dict-data.table.columns.dictType')" align="center">
            <template #default="scope">
              {{ scope.row.dictType }}
            </template>
          </tiny-grid-column>
          <tiny-grid-column field="status" :title="$t('global.table.columns.status')" align="center">
            <template #default="scope">
              <dict-tag :value="scope.row.status" :options="proxy.$dict.getDict('sys_common_data_status')" />
            </template>
          </tiny-grid-column>
          <tiny-grid-column field="presetFlag" :title="$t('global.table.columns.presetFlag')" align="center">
            <template #default="scope">
              <dict-tag :value="scope.row.presetFlag" :options="proxy.$dict.getDict('sys_common_data_preset_flag')" />
            </template>
          </tiny-grid-column>
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
import SystemRequest from '@/api/system/index';
import editform from './components/edit-form.vue';

const { proxy } = getCurrentInstance() as any;

const dictTypeOptions: Ref<SystemDictAPI.DictTypeVO[]> = ref([]);
const queryAll = (query: SystemDictAPI.DictTypeAllQuery) => {
  SystemRequest.dictType.queryDictTypeAll(toRaw(query)).then((res) => {
    dictTypeOptions.value = res.data;
  });
};
queryAll({});

const state = reactive<{
  loading: boolean;
  filterOptions: SystemDictAPI.DictDataPageQuery;
}>({
  loading: false,
  filterOptions: {
    dictType: proxy.$route.query.dictType || '',
  },
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
  },
});

async function getPageData(
  params: SystemDictAPI.DictDataPageQuery = {
    pageNo: 1,
    pageSize: 10,
  }
) {
  const queryParmas: SystemDictAPI.DictDataPageQuery = {
    ...filterOptions.value,
    ...params,
  };
  state.loading = true;
  try {
    const { data } = await SystemRequest.dictData.queryDictDataPage(
      queryParmas
    );
    const { records, total } = data;
    return {
      result: records,
      page: { total },
    };
  } finally {
    state.loading = false;
  }
}

const options = ref([
  {
    label: 'global.table.operations.edit',
  },
  {
    label: 'global.table.operations.delete',
  },
]);

const editFormRef = ref();

const optionsClick = (label: string, data: SystemDictAPI.DictDataVO) => {
  switch (label) {
    case 'global.table.operations.edit': {
      editFormRef.value.open(data.id);
      break;
    }
    case 'global.table.operations.delete': {
      handleDelete(data);
      break;
    }
    default:
      console.log('code is error.');
  }
};

const handleDelete = (data: SystemDictAPI.DictDataVO) => {
  proxy.$modal
    .confirm({
      message: `确定要删除字典【${data.dictLabel}】吗?`,
      maskClosable: true,
      title: '删除提示',
    })
    .then((res: string) => {
      if (data.id && res === 'confirm') {
        SystemRequest.dictData.deleteDictDataById(data.id).then(() => {
          handleFormQuery();
          proxy.$modal.message({ message: '删除成功', status: 'success' });
        });
      }
    });
};

const handleFormQuery = () => {
  gridTableRef?.value.handleFetch('reload');
};
const handleFormReset = () => {
  state.filterOptions = {} as SystemDictAPI.DictDataPageQuery;
  handleFormQuery();
};

const toolbarButtons = reactive([
  {
    code: 'insert',
    name: '新增',
  },
  {
    code: 'batchDelete',
    name: '批量删除',
  },
]);

const toolbarButtonClickEvent = ({ code, $grid }: any) => {
  const data = $grid.getSelectRecords();
  switch (code) {
    case 'insert': {
      editFormRef.value.open();
      break;
    }
    case 'batchDelete': {
      editFormRef.value.open();
      break;
    }
    default:
      console.log('code is error.');
  }
};
</script>

<style scoped lang="less"></style>