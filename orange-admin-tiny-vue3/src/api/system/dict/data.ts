import axios from 'axios';

const BASE_URL = "/system/dict/v1.0/dict-data"

export function queryDictDataPage(params: SystemDictAPI.DictDataPageQueryParams) {
  return axios.post(BASE_URL.concat("/page"), params);
}

export function queryDictDataAll(params: SystemDictAPI.DictDataAllQueryParams) {
  return axios.post(BASE_URL.concat("/query-all"), params);
}

export function queryDictDataListByType(dictType: string) {
  return axios.get(BASE_URL.concat(`/query-by-type/${dictType}`));
}


export function deleteDictDataById(id: string) {
  return axios.delete(BASE_URL.concat(`/${id}`));
}

export function addDictData(params: SystemDictAPI.DictDataVO) {
  return axios.post(BASE_URL, params);
}

export function getDictDataById(id: string) {
  return axios.get(BASE_URL.concat(`/${id}`));
}

export function updateDictDataById(id: string, params: SystemDictAPI.DictDataVO) {
  return axios.put(BASE_URL.concat(`/${id}`), params);
}
