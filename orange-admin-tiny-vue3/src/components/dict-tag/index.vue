<template>
  <template v-for="(item, index) in options">
    <template v-if="values.includes(String(item.dictValue))">
      <tiny-tag :key="item.dictValue" :index="index" :color="item.showStyle" style="color: #fff;">
        {{ item.dictLabel }}
      </tiny-tag>
    </template>
  </template>
</template>

<script setup lang="ts">
import { computed } from 'vue';

const props = defineProps({
  // 数据
  options: {
    type: Array<SystemDictAPI.DictDataVO>,
    default: null
  },
  // 当前的值
  value: [Number, String, Array]
})

const values = computed(() => {
  if (props.value !== null && typeof props.value !== 'undefined') {
    return Array.isArray(props.value) ? props.value : [String(props.value)]
  }
  return []
})
</script>

<style scoped></style>
