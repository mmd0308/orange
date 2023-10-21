<template>
  <div class="card-container">
    <h3>版本</h3>
    <tiny-collapse v-model="activeNames">
      <tiny-collapse-item title="1.0.0" name="100">
        <div>
          更新日志：
          <tiny-link :underline="false" href="http://hengzq.cn/orange/admin/version/v1.0.0.html" target="_blank"
            type="primary">
            http://hengzq.cn/orange/admin/version/v1.0.0.html
          </tiny-link>
        </div>
      </tiny-collapse-item>
    </tiny-collapse>
  </div>
</template>

<script lang="ts" setup>
const activeNames = ['100']


</script>