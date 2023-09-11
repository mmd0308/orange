declare namespace SystemPermissionAPI {

  interface DepartmentVO {
    tenantId?: string;
    id?: string;
    parentId?: number;
    name?: string;
    sort?: number;
  };

  interface DepartmentAllQueryParams {
    parentId?: number;
    name?: string;
  };

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

  interface RoleAllQueryParams {
    name?: string;
    nameLike?: string;
    permission?: string;
    permissionLike?: string;
    status?: 'NORMAL' | 'DISABLE';
  };

  interface RolePageQueryParams extends RoleAllQueryParams {
    pageNo?: number;
    pageSize?: number;
  };


}