import { RoleType } from '@/types/roleType';

export default {
  path: 'system/permission',
  name: 'SystemPermission',
  id: 'SystemPermission',
  label: 'SystemPermission',
  redirect: `${import.meta.env.VITE_CONTEXT}system/permission/department`,
  component: () => import('@/views/index.vue'),
  meta: {
    locale: 'menu.system.permission',
    requiresAuth: true,
    order: 7,
    roles: [RoleType.admin, RoleType.user],
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
        roles: [RoleType.admin, RoleType.user],
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
        roles: [RoleType.admin, RoleType.user],
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
        roles: [RoleType.admin, RoleType.user],
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
        roles: [RoleType.admin, RoleType.user],
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
        roles: [RoleType.admin, RoleType.user],
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
        roles: [RoleType.admin, RoleType.user],
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
        roles: [RoleType.admin, RoleType.user],
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
        roles: [RoleType.admin, RoleType.user],
      },
    }
  ],

};
