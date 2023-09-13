import axios, { AxiosRequestConfig, AxiosResponse } from 'axios';
import { Modal } from '@opentiny/vue';
import locale from '@opentiny/vue-locale';
import router from '@/router';
import { getToken, clearToken } from '@/utils/auth';

export interface HttpResponse<T = unknown> {
  msg: string;
  code: string | number;
  data: T;
}

const { VITE_API_BASE_URL, VITE_BASE_API, VITE_MOCK_IGNORE } =
  import.meta.env || {};

if (VITE_API_BASE_URL) {
  axios.defaults.baseURL = VITE_API_BASE_URL;
}

const ignoreMockApiList = VITE_MOCK_IGNORE?.split(',') || [];


axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'


axios.interceptors.request.use(
  (config: AxiosRequestConfig) => {
    // const isProxy = ignoreMockApiList.includes(config.url);
    // debugger
    // if (isProxy) {
    //   config.url = config.url?.replace(VITE_BASE_API, '/api/v1');
    // }
    // const token = getToken();
    // if (token) {
    //   if (!config.headers) {
    //     config.headers = {};
    //   }
    //   config.headers.Authorization = `Bearer ${token}`;
    // }

    config.headers["orange-token"] = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJoZW5nenEiLCJpYXQiOjE2OTQ0OTcyNzEsImV4cCI6MTY5NDU0MDQ3MSwiaWQiOi0xMDAsInVzZXJuYW1lIjoiYWRtaW4iLCJ0ZW5hbnRJZCI6LTEwMH0.CqGV-i3BAuA1jZHCt5yJH1oPQM_jUwHcRowFrKsiOrw'
    config.headers = { ...config.headers };

    return config;
  },
  (error) => {
    console.log(error)
  }
);

axios.interceptors.response.use(
  (response: AxiosResponse<HttpResponse>) => {
    // 接口响应数据
    const result = response.data;
    // http请求状态码为200,接口响应码为200 表示请求成功
    if (result.code === '200' || result.code === 200) {
      //  {code: '200', msg: '操作成功', data: {…}}
      // 返回整个response
      return result
    }
    result.msg &&
      Modal.message({
        message: result.msg,
        status: 'error',
      });
    return Promise.reject(new Error(result.msg || 'Error'));
  },
  (error) => {
    const { status, data } = error.response;
    if (status === 401) {
      clearToken();
      router.replace({ name: 'login' });
      Modal.message({
        message: locale.t('http.error.TokenExpire'),
        status: 'error',
      });
    } else {
      data.errMsg &&
        Modal.message({
          message: locale.t(`http.error.${data.errMsg}`),
          status: 'error',
        });
    }

    return Promise.reject(error);
  }
);

