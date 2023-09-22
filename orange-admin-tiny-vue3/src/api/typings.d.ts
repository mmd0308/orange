declare namespace API {
  interface Result<T> {
    /** 编码 200表示成功，其他值表示失败 */
    code?: number;
    /** 消息内容 */
    msg?: string;
    /** 请求ID */
    requestId?: string;
    /** 响应的数据 */
    data?: T;
  };
}