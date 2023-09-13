import axios from 'axios';

const BASE_URL = "/system/dict/v1.0/dict-type"

export function queryDictTypePage(params: SystemDictAPI.DictTypePageQueryParams) {
  return axios.post(BASE_URL.concat("/page"), params);
}

export function queryDictTypeAll(params: SystemDictAPI.DictTypeAllQueryParams) {
  return axios.post(BASE_URL.concat("/query-all"), params);
}

export function deleteDictTypeById(id: string) {
  return axios.delete(BASE_URL.concat(`/${id}`));
}

export function addDictType(params: SystemDictAPI.DictTypeVO) {
  return axios.post(BASE_URL, params);
}

export function getDictTypeById(id: string) {
  return axios.get(BASE_URL.concat(`/${id}`));
}

export function updateDictTypeById(id: string, params: SystemDictAPI.DictTypeVO) {
  return axios.put(BASE_URL.concat(`/${id}`), params);
}
