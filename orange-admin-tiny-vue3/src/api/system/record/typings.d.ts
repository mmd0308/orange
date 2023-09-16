declare namespace SystemRecordAPI {
  interface RecordLoginPageQuery {
    /** 查询页码 */
    pageNo?: number;
    /** 每页数量 */
    pageSize?: number;
    /** 登录账号 */
    account?: string;
    /** 开始时间 第一个为开始时间 第二个为结束时间 */
    loginTime?: string[];
  };

  interface RecordLoginVO {
    id?: number;
    traceId?: string;
    account?: string;
    type?: 'LOGIN' | 'LOGOUT';
    userId?: number;
    userIp?: string;
    userAgent?: string;
    loginTime?: string;
    userLocation?: string;
    status?: 'SUCCESS' | 'FAIL';
  };


  interface RecordOperationPageQuery extends RecordOperationAllQuery {
    /** 查询页码，从1开始 默认值:1 */
    pageNo?: number;
    /** 每页数量,取值范围[5,500] 默认值:10 */
    pageSize?: number;
  };

  interface RecordOperationAllQuery {
    /** 请求ID */
    traceId?: string;
    /** 请求状态 */
    status?: 'SUCCESS' | 'FAIL';
    operationStartTime?: string;
    operationEndTime?: string;
  }

  interface RecordOperationVO {
    /** 租户id */
    tenantId?: number;
    /** 主键 */
    id?: number;
    /** 请求ID */
    traceId?: string;
    /** 资源id */
    resourceId?: string;
    /** User-Agent */
    userAgent?: string;
    /** 请求URL */
    requestUrl?: string;
    /** 请求方式 */
    requestMethod?: 'GET' | 'POST' | 'PUT' | 'PATCH' | 'DELETE';
    /** 操作用户 */
    userId?: string;
    /** 操作用户IP */
    userIp?: string;
    /** 用户操作地点 */
    userLocation?: string;
    /** 用户浏览器 */
    userBrowser?: string;
    /** 用户操作系统 */
    userOs?: string;
    /** 开始时间 */
    startTime?: string;
    /** 结束时间 */
    endTime?: string;
    /** 请求耗时,单位毫秒 */
    executeTime?: number;
    /** 请求状态 */
    status?: 'SUCCESS' | 'FAIL';
    /** Java 方法名 */
    javaMethod?: string;
    /** Java 方法名参数 */
    javaMethodArgs?: string;
    /** Java 方法返回结果 */
    javaMethodResult?: string;
    stackTrace?: string;
  };

}