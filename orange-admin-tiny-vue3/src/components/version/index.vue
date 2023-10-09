<template>
  <div class="card-container">
    <h3>版本1</h3>
    <tiny-collapse v-model="activeNames">
      <tiny-collapse-item title="1.0.0" name="100">
        <div>
          更新日志：
          <tiny-link :underline="false" href="http://hengzq.cn/orange/10.%E6%9B%B4%E6%96%B0%E6%97%A5%E5%BF%97.html#_1-0-0"
            target="_blank" type="primary">
            http://hengzq.cn/orange
          </tiny-link>
        </div>
      </tiny-collapse-item>
    </tiny-collapse>
  </div>
</template>

<script lang="ts" setup>
const activeNames = ['100']
</script>