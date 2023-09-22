export default {
  path: 'tools',
  name: 'Tools',
  id: 'Tools',
  label: '工具',
  meta: {
    locale: 'menu.system.permission',
    requiresAuth: true,
    order: 7,
  },
  children: [
    {
      path: 'icon',
      name: 'ToolsIcon',
      id: 'ToolsIcon',
      label: '图标',
      component: () => import('@/views/tools/icon/index.vue'),
      meta: {
        locale: 'menu.system.permission.department',
        requiresAuth: true,
      },
    }
  ],

};
