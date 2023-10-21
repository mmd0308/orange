import type { Router } from 'vue-router';
import NProgress from 'nprogress';
import { isLogin } from '@/utils/auth'
import { useUserStore } from '@/store';

const whiteList = [`${import.meta.env.VITE_CONTEXT}login`,]

export default function setupPermissionGuard(router: Router) {
  router.beforeEach(async (to, from, next) => {
    NProgress.start();
    if (isLogin()) {
      if (to.path === `${import.meta.env.VITE_CONTEXT}login`) {
        next({ path: `${import.meta.env.VITE_CONTEXT}dashboard` })
      } else {
        const userStore = useUserStore()
        if (JSON.stringify(userStore.user) != "{}") {
          next()
        } else {
          // 加载用户信息
          await userStore.info();
          next({ ...to, replace: true })
        }
      }
    } else {
      if (whiteList.indexOf(to.path) !== -1) {
        next()
      } else {
        next(`${import.meta.env.VITE_CONTEXT}login?redirect=${to.path}`)
      }
    }
  })
}
