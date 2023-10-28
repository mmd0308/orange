import axios from 'axios';

const BASE_URL = "/system/file/v1.0/config"

export function queryConfigPage(params: SystemFileAPI.ConfigPageQuery) {
  return axios.post(BASE_URL.concat("/page"), params);
}

export function queryAllStorages() {
  return axios.get(BASE_URL.concat("/query-all-storages"));
}

export function queryConfigAll(params: SystemFileAPI.ConfigAllQuery) {
  return axios.post(BASE_URL.concat("/all"), params);
}

export function deleteConfigById(id: string) {
  return axios.delete(BASE_URL.concat(`/${id}`));
}

export function getConfigById(id: string) {
  return axios.get(BASE_URL.concat(`/${id}`));
}


export function updateConfigById(id: string, params: SystemFileAPI.ConfigVO) {
  return axios.put(BASE_URL.concat(`/${id}`), params);
}

export function updateMasterById(id: string) {
  return axios.put(BASE_URL.concat(`/master/${id}`));
}

export function addConfig(params: SystemFileAPI.ConfigVO) {
  return axios.post(BASE_URL, params);
}
