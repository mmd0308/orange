<template>
  <tiny-dropdown class="user-center-dropdown">
    <tiny-user-head type="icon" round min>
      <div class="user-image">
        <img src="@/assets/images/avatar.png" alt="user" />
      </div>
    </tiny-user-head>
    <template #dropdown>
      <tiny-dropdown-menu>
        <tiny-dropdown-item @click="toUserSetting">
          个人设置
        </tiny-dropdown-item>
        <tiny-dropdown-item @click="toDoc">
          项目文档
        </tiny-dropdown-item>
        <tiny-dropdown-item divided @click="logout">
          {{ $t('messageBox.logout') }}
        </tiny-dropdown-item>
      </tiny-dropdown-menu>
    </template>
  </tiny-dropdown>
</template>

<script lang="ts" setup>
import { useI18n } from 'vue-i18n';
import { useAppStore, useUserStore } from '@/store';
import router from '@/router';

const { proxy } = getCurrentInstance() as any

const userStore = useUserStore();
const { t } = useI18n();

const toUserSetting = () => {
  proxy.$modal.message("开发中...")
  // router.push({
  //   path: `${import.meta.env.VITE_CONTEXT}system/permission/user-setting`
  // });
}

const toDoc = () => {
  window.open("http://hengzq.cn/orange/admin/01.%E4%BB%8B%E7%BB%8D.html", "_blank");
}

const logout = async () => {
  await userStore.logout()
  const currentRoute = router.currentRoute.value;
  proxy.$modal.message({ message: t('setting.loginout'), status: 'success', });

  router.push({
    path: `${import.meta.env.VITE_CONTEXT}login`,
    query: {
      ...router.currentRoute.value.query,
      redirect: currentRoute.fullPath as string,
    },
  });
}

</script>

<style lang="less" >
.user-center-dropdown {
  .tiny-dropdown__suffix-inner {
    display: none !important
  }

  .user-image {
    display: flex;
    flex-direction: column;
    height: 100%;
    font-weight: 600;
    font-size: 2em;
    font-style: oblique;
    cursor: pointer;
    fill: var(--ti-common-color-line-active);
  }
}
</style>