import axios from 'axios';

const BASE_URL = "/system/permission/v1.0/permission"


export function allotUserRole(params: SystemPermissionAPI.AllotUserRoleVO) {
  return axios.post(BASE_URL.concat("/allot_user_role"), params);
}

export function allotRoleResource(params: SystemPermissionAPI.AllotRoleResourceRequest) {
  return axios.post(BASE_URL.concat("/allot_role_resource"), params);
}


export function queryRoutersTree() {
  return axios.get(BASE_URL.concat("/query-current-user-routers-tree"));
}

export function queryResourceIdsByRoleId(roleId: string) {
  return axios.get(BASE_URL.concat(`/query_resource_ids/${roleId}`));
}

// export function queryRoleAll(params: SystemPermissionAPI.RoleAllQueryParams) {
//   return axios.post(BASE_URL.concat("/all"), params);
// }

// export function deleteRoleById(id: string) {
//   return axios.delete(BASE_URL.concat(`/${id}`));
// }

// export function addRole(params: SystemPermissionAPI.RoleVO) {
//   return axios.post(BASE_URL, params);
// }

// export function getRoleById(id: string) {
//   return axios.get(BASE_URL.concat(`/${id}`));
// }

// export function updateRoleById(id: string, params: SystemPermissionAPI.RoleVO) {
//   return axios.put(BASE_URL.concat(`/${id}`), params);
// }
