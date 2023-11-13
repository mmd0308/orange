export default {
  path: 'system/permission',
  name: 'SystemPermission',
  id: 'SystemPermission',
  label: 'SystemPermission',
  meta: {
    locale: 'menu.system.permission',
    requiresAuth: true,
    order: 7,
  },
  children: [
    {
      path: 'department',
      name: 'SystemPermissionDepartment',
      id: 'SystemPermissionDepartment',
      label: 'SystemPermissionDepartment',
      component: () => import('@/views/admin/system/permission/department/index.vue'),
      meta: {
        locale: 'menu.system.permission.department',
        requiresAuth: true,
      },
    },
    {
      path: 'user',
      name: 'SystemPermissionUser',
      id: 'SystemPermissionUser',
      label: 'SystemPermissionUser',
      component: () => import('@/views/admin/system/permission/user/index.vue'),
      meta: {
        locale: 'menu.system.permission.user',
        requiresAuth: true,
      },
    },
    {
      path: 'user-setting',
      name: 'UserSetting',
      id: 'UserSetting',
      label: 'UserSetting',
      component: () => import('@/views/admin/system/permission/user/setting/index.vue'),
      meta: {
        locale: 'menu.system.permission.user',
        requiresAuth: true,
      },
    },
    {
      path: 'role',
      name: 'SystemPermissionRole',
      id: 'SystemPermissionRole',
      label: 'SystemPermissionRole',
      component: () => import('@/views/admin/system/permission/role/index.vue'),
      meta: {
        locale: 'menu.system.permission.role',
        requiresAuth: true,
      },
    },
    {
      path: 'menu',
      name: 'SystemPermissionMenu',
      id: 'SystemPermissionMenu',
      label: '菜单管理',
      component: () => import('@/views/admin/system/permission/menu/index.vue'),
      meta: {
        locale: 'menu.system.permission.menu',
        requiresAuth: true,
      },
    },
    {
      path: 'dict',
      name: 'SystemPermissionDict',
      id: 'SystemPermissionDict',
      label: 'SystemPermissionDict',
      component: () => import('@/views/admin/system/dict/type/index.vue'),
      meta: {
        locale: 'menu.system.dict.type',
        requiresAuth: true,
      },
    },
    {
      path: 'dict-data',
      name: 'SystemPermissionDictData',
      id: 'SystemPermissionDictData',
      label: 'SystemPermissionDictData',
      component: () => import('@/views/admin/system/dict/data/index.vue'),
      meta: {
        locale: 'menu.system.dict.data',
        requiresAuth: true,
      },
    },
    {
      path: 'login-log',
      name: 'SystemPermissionLoginLog',
      id: 'SystemPermissionLoginLog',
      label: 'SystemPermissionLoginLog',
      component: () => import('@/views/admin/system/record/login/index.vue'),
      meta: {
        locale: 'menu.system.record.login',
        requiresAuth: true,
      },
    },
    {
      path: 'operation-log',
      name: 'SystemPermissionOperationLog',
      id: 'SystemPermissionOperationLog',
      label: 'SystemPermissionOperationLog',
      component: () => import('@/views/admin/system/record/operation/index.vue'),
      meta: {
        locale: 'menu.system.record.operation',
        requiresAuth: true,
      },
    }
  ],

};
