<template>
  <div class="container-list">
    <tiny-form :model="filterOptions" label-position="right" label-width="100px" class="filter-form" size="small">
      <tiny-row :flex="true" justify="center" class="col">
        <tiny-col :span="4" label-width="100px">
          <tiny-form-item :label="$t('searchTable.columns.name')">
            <tiny-input v-model="filterOptions.name" :placeholder="$t('searchTable.form.input')"></tiny-input>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="4" label-width="100px">
          <tiny-form-item :label="$t('searchTable.columns.department')" prop="id">
            <tiny-input v-model="filterOptions.department" :placeholder="$t('searchTable.form.input')"></tiny-input>
          </tiny-form-item>
        </tiny-col>
        <tiny-col :span="4" label-width="100px">
          <tiny-form-item>
            <div class="search-btn">
              <tiny-button type="primary" @click="reloadGrid">
                {{ $t( 'global.form.search' ) }}
              </tiny-button>
              <tiny-button @click="handleFormReset">
                {{ $t( 'global.form.reset' ) }}
              </tiny-button>
              <tiny-button v-if="setCollapse" @click="collapse ">
                {{
                $t( 'searchTable.form.collapse' )
                }}
              </tiny-button>
              <tiny-button v-else @click="extend ">
                {{
                $t( 'searchTable.form.extend' )
                }}
              </tiny-button>
            </div>
          </tiny-form-item>
        </tiny-col>
      </tiny-row>
    </tiny-form>
    <div class="bottom-line">
      <hr />
    </div>
    <div class="tiny-fullscreen-scroll">
      <div class="tiny-fullscreen-wrapper">
        <tiny-grid ref="taskGrid" :fetch-data="fetchTableData" :pager="pagerConfig" :loading="loading"
          :auto-resize="true" @toolbar-button-click="toolbarButtonClickEvent">
          <template #toolbar>
            <tiny-grid-toolbar :buttons="toolbarButtons" refresh full-screen />
          </template>

          <tiny-grid-column field="name" :title="$t('system.role.table.columns.name')" align="center" />
          <tiny-grid-column field="permission" :title="$t('system.role.table.columns.permission')" align="center" />
          <tiny-grid-column field="status" :title="$t('global.table.column.status')" align="center" />
          <tiny-grid-column field="sort" :title="$t('global.table.column.sort')" align="center" />
          <tiny-grid-column field="createdAt" :title="$t('global.table.column.createdAt')" align="center" />

          <tiny-grid-column :title="$t('searchTable.columns.operations')" align="center">
            <template v-slot="data">
              <a class="operation" @click="handleDelete(data.row.id)">
                {{ $t( 'searchTable.columns.operations.delete' ) }}
              </a>
            </template>
          </tiny-grid-column>
        </tiny-grid>
      </div>
    </div>

    <edit-form ref="editFormRef" @ok="fetchTableData"></edit-form>
  </div>
</template>

<script lang="ts" setup>
  import { ref, reactive, toRefs } from 'vue';

  import {
    Grid as TinyGrid,
    GridColumn as TinyGridColumn,
    GridToolbar as TinyGridToolbar,
    Form as TinyForm,
    FormItem as TinyFormItem,
    Input as TinyInput,
    Button as TinyButton,
    Row as TinyRow,
    Col as TinyCol,
    Select as TinySelect,
    Pager as TinyPager,
    Fullscreen as TinyFullscreen, Modal,
  } from '@opentiny/vue';

  import {
    queryEmployeeList,
    deleteEmployee,
    QueryTaskParmas,
  } from '@/api/list';

  import {
    RolePageQueryParmas,
    queryPage,
  } from '@/api/system/role';

  import editform from './components/edit-form.vue';

  const editFormRef = ref();

  // 初始化请求数据
  interface FilterOptions {
    id: string;
    department: string;
    roles: string;
    dateRange: Array<string | Date>;
    name: string;
    status: string;
    workbenchName: string;
    project: string;
    type: string;
    address: string;
  }

  // 加载效果
  const state = reactive < {
    loading: boolean;
    filterOptions: FilterOptions;
  } > ({
    loading: false,
    filterOptions: {} as FilterOptions,
  });

  const pagerConfig = reactive({
    component: TinyPager,
    attrs: {
      currentPage: 1,
      pageSize: 10,
      pageSizes: [10, 20, 30, 50, 100],
      total: 10,
      layout: 'total, prev, pager, next, jumper, sizes',
    },
  });



  const taskGrid = ref();
  const { loading, filterOptions } = toRefs(state);


  const fetchTableData = reactive({
    api: getPageData
  });


  // 请求数据接口方法
  async function getPageData ({ page }) {
    const params: RolePageQueryParmas = {
      pageNo: page.currentPage,
      pageSize: page.pageSize,
    }
    const { ...rest } = filterOptions.value;
    const queryParmas = {
      ...rest,
      ...params,
    };

    state.loading = true;
    try {
      const { data } = await queryPage(queryParmas);
      const { records, total } = data;
      return {
        result: records,
        page: { total },
      };
    } finally {
      state.loading = false;
    }
  }


  const handleDelete = (id: string) => {
    deleteEmployee(id).then((res) => {
      Modal.message({
        message: '已删除',
        status: 'success',
      });
    })
  }

  // form的button
  function reloadGrid () {
    taskGrid?.value.handleFetch('reload');
    getPageData();
  }

  function handleFormReset () {
    state.filterOptions = {} as FilterOptions;
    reloadGrid();
  }

  const setCollapse = ref(true);

  function collapse () {
    setCollapse.value = false;
  }

  function extend () {
    setCollapse.value = true;
  }

  const toolbarButtons = reactive([
    {
      code: 'insert',
      name: '新增'
    },
    {
      code: 'delete',
      name: '删除'
    }
  ])


  function toolbarButtonClickEvent ({ code, $grid }) {
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

<style scoped lang="less">

</style>