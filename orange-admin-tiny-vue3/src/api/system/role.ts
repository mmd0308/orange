import axios from 'axios';

const BASE_URL = "/system/permission/v1.0/role"

export interface RolePageQueryParmas {
  pageNo: number;
  pageSize: number;
  [key: string]: any;
}

export function queryPage(params: RolePageQueryParmas) {
  return axios.post("/system/permission/v1.0/role/page", params);
}

