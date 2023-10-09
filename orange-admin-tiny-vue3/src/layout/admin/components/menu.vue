<template>
  <div class="menu-router">
    <tiny-tree-menu :data="treeData" :show-filter="false" @current-change="currentChange">
      <template #default="scope">
        <svg-icon :name="scope.data.icon" class="svg-icon" />
        <a>
          {{ scope.data.name }}
        </a>
      </template>
    </tiny-tree-menu>
  </div>
</template>

<script lang="ts" setup>
import router from '@/router';
// import { useUserStore } from '@/store';
import { isExternal } from '@/utils/validate'
import SystemRequest from '@/api/system/index'

const currentChange = (data: any) => {
  if (data.path) {
    if (isExternal(data.path)) {
      window.open(data.path, "_blank");
    } else {
      router.push(`${import.meta.env.VITE_CONTEXT}${data.path}`);
    }
  }
}
const treeData = ref([])

const getMenuDataSync = async () => {
  const { data } = await SystemRequest.permission.queryRoutersTree();
  treeData.value = data
}

getMenuDataSync()
</script>

<style>
.svg-icon {
  margin-right: 12px;
  vertical-align: -0.15em;
}
</style>