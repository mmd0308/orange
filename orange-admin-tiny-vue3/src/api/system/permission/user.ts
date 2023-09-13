import axios from 'axios';

const BASE_URL = "/system/permission/v1.0/user"

export function queryUserPage(params: SystemPermissionAPI.UserPageQueryParams) {
  return axios.post(BASE_URL.concat("/page"), params);
}

export function queryUserAll(params: SystemPermissionAPI.UserAllQueryParams) {
  return axios.post(BASE_URL.concat("/query-all"), params);
}

export function deleteUserById(id: string) {
  return axios.delete(BASE_URL.concat(`/${id}`));
}

export function addUser(params: SystemPermissionAPI.UserVO) {
  return axios.post(BASE_URL, params);
}

export function getUserById(id: string) {
  return axios.get(BASE_URL.concat(`/${id}`));
}

export function updateUserById(id: string, params: SystemPermissionAPI.UserVO) {
  return axios.put(BASE_URL.concat(`/${id}`), params);
}
