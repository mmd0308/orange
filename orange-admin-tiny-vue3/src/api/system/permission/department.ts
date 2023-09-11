import axios from 'axios';

const BASE_URL = "/system/permission/v1.0/department"

export function queryDepartmentAll(params: SystemPermissionAPI.DepartmentAllQueryParams) {
  return axios.post(BASE_URL.concat("/query-all"), params);
}

export function deleteDepartmentById(id: string) {
  return axios.delete(BASE_URL.concat(`/${id}`));
}

export function addDepartment(params: SystemPermissionAPI.DepartmentVO) {
  return axios.post(BASE_URL, params);
}

export function getDepartmentById(id: string) {
  return axios.get(BASE_URL.concat(`/${id}`));
}

export function updateDepartmentById(id: string, params: SystemPermissionAPI.DepartmentVO) {
  return axios.put(BASE_URL.concat(`/${id}`), params);
}
