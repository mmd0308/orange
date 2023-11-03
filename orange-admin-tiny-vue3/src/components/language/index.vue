<template>
  <svg-icon v-if="currentLocale === 'zhCN'" name="system-language" width="20px" height="20px"
    @click="changeLocale('enUS')" />
  <svg-icon v-else name="system-text" width="20px" height="20px" @click="changeLocale('zhCN')" />
</template>

<script lang="ts" setup>
import { LOCALE_OPTIONS } from '@/locale';
import useLocale from '@/plugins/locale';

const { changeLocale, currentLocale } = useLocale();
const locales = [...LOCALE_OPTIONS];
</script>

