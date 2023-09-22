import axios from 'axios';

const BASE_URL = "/system/permission/v1.0/auth"

export function login(params: SystemPermissionAPI.LoginParams) {
  return axios.post(BASE_URL.concat("/login"), params);
}

export function getUserInfo() {
  return axios.get(BASE_URL.concat("/info"));
}
