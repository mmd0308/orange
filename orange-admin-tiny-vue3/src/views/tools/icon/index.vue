<template>
  <div style="background:#fff;padding: 20px;">
    <tiny-tabs v-model="activeName">
      <tiny-tab-item title="System" name="system">
        <tiny-popover v-for="item in systemIds" :key="item" placement="top-start" width="200" trigger="hover"
          append-to-body :content='`<svg-icon name="${item}"/>`'>
          <template #reference>
            <div class="icon-item" @click="copyIcon(item.substr(5))">
              <svg-icon :name="item.substr(5)" width="30" height="30" />
              <span>{{ item.substr(5) }}</span>
            </div>
          </template>
        </tiny-popover>
      </tiny-tab-item>
      <tiny-tab-item title="Logo" name="logo">
        <tiny-popover v-for="item in logoIds" :key="item" placement="top-start" width="200" trigger="hover" append-to-body
          :content='`<svg-icon name="${item}"/>`'>
          <template #reference>
            <div class="icon-item" @click="copyIcon(item.substr(5))">
              <svg-icon :name="item.substr(5)" width="30" height="30" />
              <span>{{ item.substr(5) }}</span>
            </div>
          </template>
        </tiny-popover>
      </tiny-tab-item>
    </tiny-tabs>
  </div>
</template>


<script lang="ts" setup>
import {
  Grid as TinyGrid, GridColumn as TinyGridColumn, GridToolbar as TinyGridToolbar,
  Form as TinyForm, FormItem as TinyFormItem,
  Input as TinyInput, Button as TinyButton,
  Row as TinyRow, Col as TinyCol,
  Modal, ActionMenu as TinyActionMenu, Tabs as TinyTabs,
  TabItem as TinyTabItem, Popover as TinyPopover,
} from '@opentiny/vue';
import { useClipboard } from '@vueuse/core';

import ids from 'virtual:svg-icons-names'

const activeName = ref("system")

const { copy } = useClipboard();

const systemIds = computed(() => ids.filter((item) => item.includes("system")))
const logoIds = computed(() => ids.filter((item) => item.includes("logo")))

const copyIcon = (id: string) => {
  const text = `<svg-icon name="${id}"/>`
  copy(text)
  Modal.message({
    message: '复制成功',
    status: 'success',
  });
}

</script>

<style scoped lang="less">
.icon-item {
  margin: 20px;
  height: 85px;
  text-align: center;
  width: 100px;
  float: left;
  font-size: 30px;
  color: #24292e;
  cursor: pointer;

  span {
    display: block;
    font-size: 12px;
    margin-top: 10px;
  }
}
</style>