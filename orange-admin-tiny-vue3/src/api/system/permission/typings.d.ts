declare namespace SystemPermissionAPI {
  type LoginParams = {
    /** 登陆账号 */
    username: string;
    /** 登陆密码 */
    password: string;
    /** 租户账号 */
    tenantId?: string;
  };

  interface DepartmentVO {
    tenantId?: string;
    id?: string;
    parentId?: string;
    name?: string;
    sort?: number;
  };

  interface DepartmentTreeVO {
    tenantId?: string;
    id?: string;
    parentId?: string;
    name?: string;
    sort?: number;
    /** 子集 */
    children?: DepartmentTreeVO[];
  };

  interface DepartmentAllQuery {
    parentId?: number;
    name?: string;
    nameLike?: string
  };

  interface UserPageQuery extends UserAllQuery {
    pageNo?: number;
    pageSize?: number;
  };

  interface UserAllQuery {
    departmentId?: string;
    name?: string;
    nameLike?: string;
    email?: string;
    username?: string;
    usernameLike?: string;
  };

  interface ResetPasswordRequest {
    userId: string;
    newPassword: string;
    confirmPassword: string;
  }

  interface UserVO {
    tenantId?: number;
    id?: string;
    departmentId?: number;
    avatar?: number;
    name?: string;
    email?: string;
    sex?: 'MALE' | 'FEMALE' | 'UNKNOWN';
    phone?: string;
    username?: string;
    password?: string;
    remark?: string;
  };

  interface UserDetailsVO extends UserVO {
    roleVOList?: RoleVO[]
  }

  interface RoleVO {
    tenantId?: string;
    id?: string;
    name: string;
    permission?: string;
    sort?: number;
    status?: 'NORMAL' | 'DISABLE';
    presetFlag?: 'PRESET' | 'CUSTOM';
    remark?: string;
  };

  interface RoleAllQuery {
    name?: string;
    nameLike?: string;
    permission?: string;
    permissionLike?: string;
    status?: 'NORMAL' | 'DISABLE';
  };

  interface RolePageQuery extends RoleAllQuery {
    pageNo?: number;
    pageSize?: number;
  };


  interface MenuVO {
    tenantId?: string;
    id?: string;
    parentId?: string;
    rootId?: string;
    name?: string;
    permission?: string;
    presetFlag?: 'PRESET' | 'CUSTOM';
    path?: string;
    icon?: string;
    component?: string;
    sort?: number;
    remark?: string;
  };

  interface MenuTreeVO extends MenuVO {
    children?: MenuTreeVO[];
  };


  interface MenuAllQuery {
    parentId?: number;
    name?: string;
    nameLike?: string;
    permission?: string;
    permissionLike?: string;
    component?: string;
    includeButton?: boolean
  };

  interface ButtonPageQuery extends ButtonAllQuery {
    /** 查询页码，从1开始 默认值:1 */
    pageNo?: number;
    /** 每页数量,取值范围[5,100] 默认值:10 */
    pageSize?: number;
  };

  interface ButtonAllQuery {
    /** 菜单ID */
    menuId: string;
  }

  type ButtonVO = {
    /** 租户id */
    tenantId?: number;
    /** 按钮ID */
    id: string;
    /** 菜单ID */
    menuId: string;
    /** rootId */
    rootId?: number;
    /** 菜单名称 */
    name: string;
    /** 权限编码 */
    permission?: string;
    /** 预设标记 */
    presetFlag?: 'PRESET' | 'CUSTOM';
    /** 请求URL */
    url?: string;
    /** 请求方式 */
    method?: 'GET' | 'POST' | 'PUT' | 'PATCH' | 'DELETE';
    /** 排序 */
    sort?: number;
    /** 备注 */
    remark?: string;
  };


  type AllotUserRoleVO = {
    /** 用户ID */
    userId: string;
    /** 角色Id */
    roleIds: string[];
  };


  interface AllotRoleResourceRequest {
    /** 角色ID */
    roleId: string;
    /** 菜单ID */
    menuIds?: string[];
    /** 按钮ID */
    buttonIds?: string[];
  };

  interface ResourceIds {
    /** 菜单ID */
    menuIds?: string[];
    /** 按钮ID */
    buttonIds?: string[];
  }
}