<template>
  <svg-icon v-if="!isFullscreen" name="system-expand" width="20px" height="20px" @click="fullScreenClick" />
  <svg-icon v-else name="system-contract" width="20px" height="20px" @click="fullScreenClick" />
</template>

<script lang="ts" setup >
// https://www.npmjs.com/package/screenfull
import screenfull from 'screenfull'

const { proxy } = getCurrentInstance() as any

const isFullscreen = ref(false)
const fullScreenClick = () => {
  if (!screenfull.isEnabled) {
    // proxy?.$message.warning('你的浏览器不支持全屏')
    return;
  }
  screenfull.toggle()
}

const change = () => {
  isFullscreen.value = screenfull.isFullscreen
}

if (screenfull.isEnabled) {
  screenfull.on('change', change)
}
</script>
