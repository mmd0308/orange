import axios from 'axios';

const BASE_URL = "/system/info/v1.0/type"

export function queryInfoTypeAll(params: SystemInfoAPI.InfoTypeAllQuery) {
  return axios.post(BASE_URL.concat("/all"), params);
}

export function deleteInfoTypeById(id: string) {
  return axios.delete(BASE_URL.concat(`/${id}`));
}

export function addInfoType(params: SystemInfoAPI.InfoTypeVO) {
  return axios.post(BASE_URL, params);
}

export function getInfoTypeById(id: string) {
  return axios.get(BASE_URL.concat(`/${id}`));
}

export function updateInfoTypeById(id: string, params: SystemInfoAPI.InfoTypeVO) {
  return axios.put(BASE_URL.concat(`/${id}`), params);
}


