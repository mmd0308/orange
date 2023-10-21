import axios from 'axios';

const BASE_URL = "/system/record/v1.0/record-operation"

export function queryRecordOperationPage(params: SystemRecordAPI.RecordOperationPageQuery) {
  return axios.post(BASE_URL.concat("/page"), params);

}

export function getRecordOperationById(id: string) {
  return axios.get(BASE_URL.concat(`/${id}`));
}

export function clear() {
  return axios.delete(BASE_URL.concat("/clear"));
}