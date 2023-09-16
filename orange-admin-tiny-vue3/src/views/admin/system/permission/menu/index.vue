<template>
  <div class="container-list">
    <tiny-form :model="filterOptions" label-position="right" label-width="100px" class="filter-form" size="small">
      <tiny-row :flex="true" justify="center">
        <tiny-col :span="4" label-width="100px">
          <tiny-form-item :label="$t('system.department.form.name')">
            <tiny-input v-model="filterOptions.nameLike" clearable
              :placeholder="$t('system.department.form.name.placeholder')"></tiny-input>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="8" label-width="100px">
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
        <tiny-grid ref="gridTableRef" :data="tableData" :loading="loading" :tree-config="{ children: 'children' }"
          :auto-resize="true" @toolbar-button-click="toolbarButtonClickEvent">
          <template #toolbar>
            <tiny-grid-toolbar :buttons="toolbarButtons" full-screen :setting="{ simple: true }" />
          </template>
          <tiny-grid-column field="index" width="50" tree-node></tiny-grid-column>
          <tiny-grid-column field="name" :title="$t('system.menu.table.columns.name')" />
          <tiny-grid-column field="icon" :title="$t('system.menu.table.columns.icon')" align="center" />
          <tiny-grid-column field="permission" :title="$t('system.menu.table.columns.permission')" width="260" />
          <tiny-grid-column field="path" :title="$t('system.menu.table.columns.path')" width="200" />
          <tiny-grid-column field="presetFlag" :title="$t('system.menu.table.columns.presetFlag')" align="center">
            <template #default="data">
              <dict-tag :value="data.row.presetFlag" :options="proxy.$dict.getDict('sys_common_data_preset_flag')" />
            </template>
          </tiny-grid-column>
          <tiny-grid-column field="sort" :title="$t('global.table.columns.sort')" align="center" />
          <tiny-grid-column field="createdAt" :title="$t('global.table.columns.createdAt')" width="135" />
          <tiny-grid-column field="updatedAt" :title="$t('global.table.columns.updatedAt')" width="135" />

          <tiny-grid-column :title="$t('global.table.operations')" align="center">
            <template #default="data">
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

const { proxy } = getCurrentInstance() as any
const state = reactive<{
  loading: boolean;
  filterOptions: SystemPermissionAPI.MenuAllQuery;
}>({
  loading: false,
  filterOptions: {} as SystemPermissionAPI.MenuAllQuery,
});


const gridTableRef = ref();
const { loading, filterOptions } = toRefs(state);

const tableData = ref<SystemPermissionAPI.DepartmentTreeVO[]>([])

async function getAllData() {
  const queryParmas: SystemPermissionAPI.MenuAllQuery = {
    ...filterOptions.value,
  };
  state.loading = true;
  try {
    const { data } = await SystemRequest.menu.queryMenuAll(queryParmas);
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
  SystemRequest.menu.deleteMenuById(id).then((res) => {
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
  state.filterOptions = {} as SystemPermissionAPI.MenuAllQuery;
  handleFormQuery();
}

function aggregateTableData(data: SystemPermissionAPI.MenuTreeVO[]) {
  const result: SystemPermissionAPI.MenuTreeVO[] = []
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