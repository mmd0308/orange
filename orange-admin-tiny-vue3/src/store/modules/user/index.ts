import { defineStore } from 'pinia';
import {
  login as userLogin,
  loginMail as userLoginMail,
  getUserInfo,
  updateUserInfo,
  LoginData,
  LoginDataMail,
} from '@/api/user';
import { setToken, clearToken } from '@/utils/auth';
import { removeRouteListener } from '@/utils/route-listener';
import { UserState, UserInfo } from './types';

const useUserStore = defineStore('user', {
  state: (): UserState => ({
    userId: '10000',
    username: 'admin',
    department: 'Tiny-Vue-Pro',
    employeeType: 'social recruitment',
    job: 'Front end',
    probationStart: '2021-04-19',
    probationEnd: '2021-10-15',
    probationDuration: '180',
    protocolStart: '2021-04-19',
    protocolEnd: '2024-04-19',
    address: '',
    status: '',
    role: 'admin',
    sort: 1,
    startTime: '',
    endTime: '',
    filterStatus: [],
    filterType: [],
    submit: false,
    reset: false,
  }),

  getters: {
    userInfo(state: UserState): UserState {
      return { ...state };
    },
  },

  actions: {
    switchRoles() {
      return new Promise((resolve) => {
        this.role = this.role === 'user' ? 'admin' : 'user';
        resolve(this.role);
      });
    },
    // Set user's information
    setInfo(partial: Partial<UserState>) {
      this.$patch(partial);
    },

    // Reset user's information
    resetInfo() {
      this.$reset();
    },

    // Reset filter information
    resetFilterInfo() {
      this.startTime = '';
      this.endTime = '';
      this.filterStatus = [];
      this.filterType = [];
    },

    // 获取用户信息
    async info() {
      const res = await getUserInfo();
      this.setInfo(res.data);
    },

    async updateInfo(data: UserInfo) {
      const res = await updateUserInfo(data);
      this.setInfo(res.data);
    },

    // 登录
    async login(loginForm: SystemPermissionAPI.LoginParams) {
      try {
        const res = await userLogin(loginForm);
        const { token } = res.data;
        setToken(token);
      } catch (err) {
        clearToken();
        throw err;
      }
    },

    // 邮箱登录
    async loginMail(loginForm: LoginDataMail) {
      try {
        const res = await userLoginMail(loginForm);
        setToken(res.data.token);
      } catch (err) {
        clearToken();
        throw err;
      }
    },

    // 推出登录
    async logout() {
      this.resetInfo();
      clearToken();
      // 清空缓存
      sessionStorage.clear()
      removeRouteListener();
    },
  },
});

export default useUserStore;
