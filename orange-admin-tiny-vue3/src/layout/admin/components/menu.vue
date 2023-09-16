<template>
  <div class="menu-router">
    <tiny-tree-menu :data="treeData" :show-filter="false" @current-change="currentChange">
      <template #default="scope">
        <a :target="scope.data.path">
          {{ scope.data.name }}
        </a>
      </template>
    </tiny-tree-menu>
  </div>
</template>

<script lang="ts" setup>
import { TreeMenu as tinyTreeMenu, TabItem } from '@opentiny/vue';
import router from '@/router';
// import { useUserStore } from '@/store';

import SystemRequest from '@/api/system/index'

// icon图标
const tree = ref();
const expandeArr = ref();


const currentChange = (data: any) => {
  if (data.path) {
    router.push(`${import.meta.env.VITE_CONTEXT}${data.path}`);
  }
}
const treeData = ref([])

const getMenuDataSync = async () => {
  const { data } = await SystemRequest.permission.queryRoutersTree();
  treeData.value = data
}

getMenuDataSync()
</script>
