<template>
  <div class="content">
    <div>notfound</div>
  </div>
</template>

<script lang="ts" setup>
import { useRouter } from 'vue-router';

const router = useRouter();
const back = () => {
  router.push({ name: 'Workplace' });
};
</script>

<style scoped lang="less"></style>
