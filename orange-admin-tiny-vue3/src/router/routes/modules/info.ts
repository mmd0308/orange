export default {
  path: 'system/info',
  name: 'SystemInfo',
  id: 'SystemInfo',
  label: '工具',
  meta: {
    locale: 'menu.system.info',
    requiresAuth: true,
    order: 7,
  },
  children: [
    {
      path: 'type',
      name: 'InfoType',
      id: 'InfoType',
      label: '消息类型',
      component: () => import('@/views/admin/system/info/type/index.vue'),
      meta: {
        locale: 'menu.system.permission.department',
        requiresAuth: true,
      },
    },
    {
      path: 'info',
      name: 'Info',
      id: 'Info',
      label: '消息管理',
      component: () => import('@/views/admin/system/info/info/index.vue'),
      meta: {
        locale: 'menu.system.permission.department',
        requiresAuth: true,
      },
    }
  ],

};
