<template>
  <div class="navbar">
    <div class="left-side">
      <div style="display: flex; align-items: center">
        <img src="/favicon.ico" style="width: 45px;" alt="logo" @click="jumpUrl" />
        <h5 @click="jumpUrl">橙子管理系统</h5>
        <!-- <div class="divider"></div> -->
        <!-- <img class="vue-icon" alt="logo" src="@/assets/images/pro.png" />
        <h4>橙子管理系统</h4> -->
      </div>
    </div>
    <ul class="right-side">
      <!-- <li>
        <input id="navbar-search" class="input-icon" :placeholder="$t('setting.input.search')" />
      </li> -->
      <!-- <li>
        <div class="divider" />
      </li> -->
      <li>
        <screenfull></screenfull>
      </li>
      <li>
        <toggle-language></toggle-language>
      </li>
      <li>
        <svg-icon name="system-settings" width="20px" height="20px" @click="setVisible" />
      </li>
      <li>
        <user-center></user-center>
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import { useI18n } from 'vue-i18n';
import {
  IconReplace,
  IconUser,
  IconCheckOut,
  IconWriting,
} from '@opentiny/vue-icon';
import { useAppStore, useUserStore } from '@/store';
import router from '@/router';
import { LOCALE_OPTIONS } from '@/locale';
import useLocale from '@/plugins/locale';
import userCenter from './navbar/user/index.vue'

const { proxy } = getCurrentInstance() as any

const i18 = useI18n();
const { t } = useI18n();
const iconReplace = IconReplace();
const iconUser = IconUser();
const iconCheckOut = IconCheckOut();
const iconWriting = IconWriting();
const lan = ref(false);

const appStore = useAppStore();
const userStore = useUserStore();
const { changeLocale } = useLocale();
const locales = [...LOCALE_OPTIONS];

// 设置页面显示
const setVisible = () => {
  appStore.updateSettings({ Settings: true });
};

// 用户设置
const userlist = [
  { label: 'messageBox.switchRoles', value: 1 },
  { label: 'messageBox.userCenter', value: 2 },
  { label: 'messageBox.userSettings', value: 3 },
  { label: 'messageBox.logout', value: 4 },
];

const switchUser = (e: number) => {
  switch (e) {
    case 2:
      router.push({ name: 'Info' });
      break;
    case 3:
      router.push({ name: 'Setting' });
      break;
    case 4:
      logout()
      break;
    default:
    // eslint-disable-next-line no-console
  }
};
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

// 点击图标跳转首页
const jumpUrl = () => {
  window.location.href = `${window.location.protocol}//${window.location.host}`;
};
</script>

<style scoped lang="less">
.navbar {
  display: flex;
  justify-content: space-between;
  height: 100%;
  background-color: #fff;
  border-bottom: 1px solid var(--color-border);
}

#navbar-search {
  width: 159px;
  height: 30px;
  padding: 14px;
  background-color: #eff1f7;
  background-position: 130px 8px;
  border: none;
  border-radius: 16px;
  outline: none;
}

.input-icon {
  background: url('@/assets/images/search.png') no-repeat scroll right center transparent;
}

.divider {
  width: 1px;
  height: 18px;
  margin: 0 2px;
  margin-top: 1px;
  background: #dfe1e6;
}

.left-side {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 60px;
  padding-left: 10px;
  cursor: pointer;

  .vue-icon {
    width: 24px;
    height: 24px;
    margin-left: 30px;
  }

  h5 {
    margin: 0 30px 0 0;
    color: var(--ti-base-common-title-color);
    font-weight: 700;
    font-size: 22px;
    line-height: 32px;
    letter-spacing: 0.55px;
  }

  h4 {
    width: 135px;
    height: 22px;
    margin-left: 10px;
    color: var(--ti-base-common-title-color);
    font-weight: normal;
    font-size: 16px;
    line-height: 22px;
    text-align: left;
  }
}

.right-side {
  display: flex;
  padding-right: 20px;
  list-style: none;

  :deep(.locale-select) {
    border-radius: 20px;
  }

  li {
    display: flex;
    align-items: center;
    padding: 0 15px;

    .item {
      display: inline-block;
      margin: 4px;
    }

    span {
      cursor: pointer;
    }

    span:hover {
      color: #2f5bea;
    }

    .navbar-lan {
      padding: 2px 0 0 2px;
    }
  }

  a {
    color: var(--color-text-1);
    text-decoration: none;
  }

  .trigger-lan {
    position: absolute;
    bottom: -44px;
    width: 100px;
    margin-left: -35px;
  }

  .trigger-lan {
    li {
      display: block;
      padding: 6px;
      font-size: 12px;
      text-align: center;
      list-style-type: none;
      background-color: #fff;
      box-shadow: 0 0 2px 2px var(--ti-common-color-bg-normal);
      cursor: pointer;
    }

    li:hover {
      color: #2f5bea;
      background-color: #f5f6f7;
    }
  }
}
</style>

<style lang="less" scoped>
// responsive
@media (max-width: @screen-ms) {
  .left-side {
    img {
      width: 38px;
    }
  }

  .right-side {
    display: none;
  }
}

@media (max-width: @screen-mm) {
  .left-side {
    img {
      width: 38px;
    }
  }

  .right-side {
    display: none;
  }
}
</style>