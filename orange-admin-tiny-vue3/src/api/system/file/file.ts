import axios from 'axios';

const BASE_URL = "/system/file/v1.0/file"

export function upload(params: FormData) {
  return axios.post(BASE_URL.concat(`/upload`), params);
}

export function queryFilePage(params: SystemFileAPI.FilePageQuery) {
  return axios.post(BASE_URL.concat("/page"), params);
}

export function queryFileAll(params: SystemFileAPI.FileAllQuery) {
  return axios.post(BASE_URL.concat("/all"), params);
}

export function deleteFileById(id: string) {
  return axios.delete(BASE_URL.concat(`/${id}`));
}

export function deleteFileByIds(params: string[]) {
  return axios.post(BASE_URL.concat(`/batch-delete`), { "ids": params });
}

export function getFileById(id: string) {
  return axios.get(BASE_URL.concat(`/${id}`));
}

export function updateFileById(id: string, params: SystemFileAPI.FileVO) {
  return axios.put(BASE_URL.concat(`/${id}`), params);
}
