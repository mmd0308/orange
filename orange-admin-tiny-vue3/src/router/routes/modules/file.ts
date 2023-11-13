export default {
  path: 'system/file',
  name: 'SystemFile',
  id: 'SystemFile',
  label: '工具',
  meta: {
    locale: 'menu.system.file',
    requiresAuth: true,
    order: 7,
  },
  children: [
    {
      path: 'config',
      name: 'FileConfig',
      id: 'FileConfig',
      label: '消息类型',
      component: () => import('@/views/admin/system/file/config/index.vue'),
      meta: {
        locale: 'menu.system.permission.department',
        requiresAuth: true,
      },
    },
    {
      path: 'file',
      name: 'FileFile',
      id: 'FileFile',
      label: '文件中心',
      component: () => import('@/views/admin/system/file/file/index.vue'),
      meta: {
        locale: 'menu.system.permission.department',
        requiresAuth: true,
      },
    }
  ],

};
