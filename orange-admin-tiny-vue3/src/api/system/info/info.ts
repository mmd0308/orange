import axios from 'axios';

const BASE_URL = "/system/info/v1.0/info"

export function queryInfoPage(params: SystemInfoAPI.InfoPageQuery) {
  return axios.post(BASE_URL.concat("/page"), params);
}

export function queryInfoAll(params: SystemInfoAPI.InfoAllQuery) {
  return axios.post(BASE_URL.concat("/all"), params);
}

export function deleteInfoById(id: string) {
  return axios.delete(BASE_URL.concat(`/${id}`));
}

export function deleteInfoByIds(params: string[]) {
  return axios.post(BASE_URL.concat(`/batch-delete`), { "ids": params });
}

export function addInfo(params: SystemInfoAPI.InfoVO) {
  return axios.post(BASE_URL, params);
}

export function getInfoById(id: string) {
  return axios.get(BASE_URL.concat(`/${id}`));
}

export function updateInfoById(id: string, params: SystemInfoAPI.InfoVO) {
  return axios.put(BASE_URL.concat(`/${id}`), params);
}
