<template>
  <div class="container-list">
    <tiny-form :model="filterOptions" label-position="right" label-width="100px" class="filter-form" size="small">
      <tiny-row :flex="true" justify="center" class="col">
        <tiny-col :span="4" label-width="100px">
          <tiny-form-item :label="$t('system.department.form.name')">
            <tiny-input v-model="filterOptions.nameLike"
              :placeholder="$t('system.department.form.name.placeholder')"></tiny-input>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="8" label-width="100px">
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
    <div class="segmentation-line">
      <hr />
    </div>
    <div class="tiny-fullscreen-scroll">
      <div class="tiny-fullscreen-wrapper">
        <tiny-grid ref="gridTableRef" :data="tableData" :loading="loading" :tree-config="{ children: 'children' }"
          :auto-resize="true" @toolbar-button-click="toolbarButtonClickEvent">
          <template #toolbar>
            <tiny-grid-toolbar :buttons="toolbarButtons" full-screen />
          </template>
          <tiny-grid-column field="name" :title="$t('system.department.table.columns.name')" align="center" tree-node />
          <tiny-grid-column field="sort" :title="$t('global.table.columns.sort')" align="center" />
          <tiny-grid-column field="createdAt" :title="$t('global.table.columns.createdAt')" align="center" />

          <tiny-grid-column :title="$t('global.table.operations')" align="center">
            <template v-slot="data">
              <tiny-button type="text" @click="handleEdit(data.row.id)"> {{
                $t('global.table.operations.edit')
              }}</tiny-button>
              <tiny-popconfirm :title="`确定要删除部门【${data.row.name}】吗?`" type="warning" trigger="click"
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


const state = reactive<{
  loading: boolean;
  filterOptions: SystemPermissionAPI.DepartmentAllQueryParams;
}>({
  loading: false,
  filterOptions: {} as SystemPermissionAPI.DepartmentAllQueryParams,
});


const gridTableRef = ref();
const { loading, filterOptions } = toRefs(state);

const tableData = ref([])

async function getAllData() {
  const queryParmas: SystemPermissionAPI.DepartmentAllQueryParams = {
    ...filterOptions.value,
  };
  state.loading = true;
  try {
    const { data } = await SystemRequest.department.queryDepartmentAll(queryParmas);
    tableData.value = aggregateTableData(data)
  } finally {
    state.loading = false
  }
}
getAllData()

const editFormRef = ref();
const handleEdit = (id: string) => {
  editFormRef.value.open(id)
}

const handleDelete = (id: string) => {
  SystemRequest.department.deleteDepartmentById(id).then((res) => {
    getAllData()
    Modal.message({
      message: '删除成功',
      status: 'success',
    });
  })
}

const handleFormQuery = () => {
  getAllData()
}
const handleFormReset = () => {
  state.filterOptions = {} as SystemPermissionAPI.DepartmentAllQueryParams;
  handleFormQuery();
}

function aggregateTableData(data) {
  const result = []
  data.forEach((item) => {
    if (item.parentId === '-1') {
      result.push(item)
    } else {
      const parent = data.find((i) => i.id === item.parentId)
      if (!parent) return
      if (!parent.children) {
        parent.children = []
      }
      parent.children.push(item)
    }
    delete item.parentId
  })
  return result
}


const toolbarButtons = reactive([
  {
    code: 'insert',
    name: '新增',
  },
  {
    code: 'export',
    name: '导出'
  }
])

const toolbarButtonClickEvent = ({ code }: any) => {
  switch (code) {
    case 'insert': {
      editFormRef.value.open()
      break
    }
    default:
      console.log("code is error.")
  }
}

</script>

<style scoped lang="less"></style>