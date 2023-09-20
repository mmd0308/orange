import axios from 'axios';

const BASE_URL = "/system/record/v1.0/record-login"

export function queryRecordLoginPage(params: SystemRecordAPI.RecordLoginPageQuery) {
  return axios.post(BASE_URL.concat("/page"), params);
}

export function getRecordLoginById(id: string) {
  return axios.get<SystemRecordAPI.RecordLoginVO>(BASE_URL.concat(`/${id}`));
}
