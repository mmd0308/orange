<template>
  <div>
    <tiny-row :flex="true" :gutter="20">
      <tiny-col :span="6">
        <version-component></version-component>
      </tiny-col>
    </tiny-row>
  </div>
</template>

<script lang="ts" setup>
import versionComponent from '@/components/version/index.vue'
</script>

<style scoped lang="less"></style>