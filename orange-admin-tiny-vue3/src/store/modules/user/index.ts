import { defineStore } from 'pinia';
import SystemRequest from '@/api/system/index'

import { setToken, clearToken } from '@/utils/auth';
import { removeRouteListener } from '@/utils/route-listener';

interface UserInfo {
  token: string,
  user: SystemPermissionAPI.UserVO,
  rolePermissions: string[],
  menuPermissions: string[],
  buttonPermissions: string[]
}

const useUserStore = defineStore('user', {
  state: (): UserInfo => ({
    token: '',
    user: {},
    rolePermissions: [],
    menuPermissions: [],
    buttonPermissions: [],
  }),

  getters: {
    // getUser(): SystemPermissionAPI.UserVO {
    //   return this.user;
    // },
    // rolePermissions(): string[] {
    //   return this.rolePermissions;
    // }
  },

  actions: {
    // 获取用户信息
    async info() {
      const { data } = await SystemRequest.auth.getUserInfo()
      const { rolePermissions, menuPermissions, buttonPermissions, user } = data
      this.user = user
      this.rolePermissions = rolePermissions
      this.menuPermissions = menuPermissions
      this.buttonPermissions = buttonPermissions
    },
    // 登录
    async login(loginForm: SystemPermissionAPI.LoginParams) {
      try {
        const res = await SystemRequest.auth.login(loginForm);
        const { token } = res.data;
        setToken(token);
      } catch (err) {
        clearToken();
        throw err;
      }
    },
    // 邮箱登录
    async loginMail(loginForm: any) {
      // try {
      //   const res = await userLoginMail(loginForm);
      //   setToken(res.data.token);
      // } catch (err) {
      //   clearToken();
      //   throw err;
      // }
    },

    // 退出登录
    async logout() {
      await SystemRequest.auth.logout();
      this.$reset();
      // 清空Token
      clearToken();
      // 清空缓存
      sessionStorage.clear()
      removeRouteListener();
    },
  },
});

export default useUserStore;
