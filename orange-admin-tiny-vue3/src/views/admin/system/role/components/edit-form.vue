<template>
  <div>
    <tiny-drawer title="标题" :visible="visible" @update:visible="visible = $event">
      <div>内容区域</div>
    </tiny-drawer>
  </div>
</template>

<script lang="ts" setup>
  import { ref } from 'vue'
  import { Drawer as TinyDrawer, Button as TinyButton } from '@opentiny/vue'

  const emit = defineEmits(['ok'])
  const visible = ref(false)


  const open = () => {
    visible.value = true
  }


  defineExpose({
    open
  })
</script>