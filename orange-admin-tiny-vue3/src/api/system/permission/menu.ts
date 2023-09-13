import axios from 'axios';

const BASE_URL = "/system/permission/v1.0/menu"

export function queryMenuPage(params: SystemPermissionAPI.MenuTreeQueryParams) {
  return axios.post(BASE_URL.concat("/page"), params);
}

export function queryMenuAll(params: SystemPermissionAPI.MenuTreeQueryParams) {
  return axios.post(BASE_URL.concat("/query-all"), params);
}

export function deleteMenuById(id: string) {
  return axios.delete(BASE_URL.concat(`/${id}`));
}

export function addMenu(params: SystemPermissionAPI.MenuVO) {
  return axios.post(BASE_URL, params);
}

export function getMenuById(id: string) {
  return axios.get(BASE_URL.concat(`/${id}`));
}

export function updateMenuById(id: string, params: SystemPermissionAPI.MenuVO) {
  return axios.put(BASE_URL.concat(`/${id}`), params);
}
