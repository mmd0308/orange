import axios from 'axios';

const BASE_URL = "/system/permission/v1.0/button"

export function queryButtonPage(params: SystemPermissionAPI.ButtonPageQueryParams) {
  return axios.post(BASE_URL.concat("/page"), params);
}

export function queryButtonAll(params: SystemPermissionAPI.ButtonAllQueryParams) {
  return axios.post(BASE_URL.concat("/query-all"), params);
}

export function deleteButtonById(id: string) {
  return axios.delete(BASE_URL.concat(`/${id}`));
}

export function addButton(params: SystemPermissionAPI.ButtonVO) {
  return axios.post(BASE_URL, params);
}

export function getButtonById(id: string) {
  return axios.get(BASE_URL.concat(`/${id}`));
}

export function updateButtonById(id: string, params: SystemPermissionAPI.ButtonVO) {
  return axios.put(BASE_URL.concat(`/${id}`), params);
}
