import { RoleType } from '@/types/roleType';

export default {
  path: 'system/permission',
  name: 'SystemPermission',
  id: 'SystemPermission',
  label: 'SystemPermission',
  component: () => import('@/views/user/index.vue'),
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
      component: () => import('@/views/admin/system/department/index.vue'),
      meta: {
        locale: 'menu.system.permission.department',
        requiresAuth: true,
        roles: [RoleType.admin, RoleType.user],
      },
    },
    {
      path: 'role',
      name: 'SystemPermissionRole',
      id: 'SystemPermissionRole',
      label: 'SystemPermissionRole',
      component: () => import('@/views/admin/system/role/index.vue'),
      meta: {
        locale: 'menu.system.permission.role',
        requiresAuth: true,
        roles: [RoleType.admin, RoleType.user],
      },
    }
  ],

};
