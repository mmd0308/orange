import axios from 'axios';

const BASE_URL = "/system/permission/v1.0/user"

export function queryUserPage(params: SystemPermissionAPI.UserPageQuery) {
  return axios.post(BASE_URL.concat("/page"), params);
}

export function queryUserAll(params: SystemPermissionAPI.UserAllQuery) {
  return axios.post(BASE_URL.concat("/query-all"), params);
}

export function deleteUserById(id: string) {
  return axios.delete(BASE_URL.concat(`/${id}`));
}

export function deleteUserByIds(params: string[]) {
  return axios.post(BASE_URL.concat(`/batch-delete`), { "ids": params });
}

export function addUser(params: SystemPermissionAPI.UserVO) {
  return axios.post(BASE_URL, params);
}

export function getUserById(id: string) {
  return axios.get(BASE_URL.concat(`/${id}`));
}

export function getUserDetailsById(id: string) {
  return axios.get(BASE_URL.concat(`/details/${id}`));
}

export function updateUserById(id: string, params: SystemPermissionAPI.UserVO) {
  return axios.put(BASE_URL.concat(`/${id}`), params);
}

export function resetUserPassword(params: SystemPermissionAPI.ResetPasswordRequest) {
  return axios.put(BASE_URL.concat(`/reset-password`), params);
}

