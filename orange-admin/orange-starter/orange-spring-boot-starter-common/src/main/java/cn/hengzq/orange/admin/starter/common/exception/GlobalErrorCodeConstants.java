package cn.hengzq.orange.admin.starter.common.exception;

/**
 * 全局错误码
 * HTTP 响应状态码 https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Status
 *
 * @author 程序员橙子
 */
public interface GlobalErrorCodeConstants {

    String PREFIX = "Global.";

    ErrorCode SUCCESS = new ErrorCode("200", "success");

    // 客户端异常码
    String GLOBAL_BAD_REQUEST_KEY = PREFIX + "400";
    ErrorCode GLOBAL_BAD_REQUEST = new ErrorCode(GLOBAL_BAD_REQUEST_KEY, "请求异常,稍后重试");
    ErrorCode GLOBAL_UNAUTHORIZED = new ErrorCode(PREFIX + "401", "请登录账号后再试");
    ErrorCode GLOBAL_FORBIDDEN = new ErrorCode(PREFIX + "403", "您没有操作该资源的权限");
    ErrorCode GLOBAL_NOT_FOUND = new ErrorCode(PREFIX + "404", "资源未找到,请联系管理人员");
    ErrorCode GLOBAL_METHOD_NOT_ALLOWED = new ErrorCode(PREFIX + "405", "请求方法异常,请联系管理人员");
    ErrorCode GLOBAL_LOCKED = new ErrorCode(PREFIX + "423", "正在访问的资源已锁定，请稍后重试");

    // 服务端异常码
    ErrorCode GLOBAL_INTERNAL_SERVER_ERROR = new ErrorCode(PREFIX + "500", "系统异常");

    // 请求相关错误提示 编码 [1000,2000)
    ErrorCode GLOBAL_REQUEST_MISSING_PARAMETER = new ErrorCode(PREFIX + "1000", "请求参数缺失");
    ErrorCode GLOBAL_REQUEST_PARAMETER_CHECK_ERROR = new ErrorCode(PREFIX + "1001", "请求参数校验失败,请检查参数");
    ErrorCode GLOBAL_REQUEST_FAILED = new ErrorCode(PREFIX + "1002", "请求失败,请稍后重试");

    // 参数相关错误提示 编码 [2000,3000)
    ErrorCode GLOBAL_PARAMETER_ID_CANNOT_NULL = new ErrorCode(PREFIX + "2000", "ID不能为空");
    ErrorCode GLOBAL_PARAMETER_ID_IS_NULL = new ErrorCode(PREFIX + "2001", "ID是空的,请检查");
    ErrorCode GLOBAL_PARAMETER_CANNOT_NULL = new ErrorCode(PREFIX + "2002", "参数不能为空");
    ErrorCode GLOBAL_PARAMETER_IS_NULL = new ErrorCode(PREFIX + "2003", "参数为空");
    ErrorCode GLOBAL_PARAMETER_IS_FALSE = new ErrorCode(PREFIX + "2004", "参数为False");
    ErrorCode GLOBAL_PARAMETER_IS_TRUE = new ErrorCode(PREFIX + "2005", "参数为True");
    ErrorCode GLOBAL_PARAMETER_CANNOT_NULL_ARGS = new ErrorCode(PREFIX + "2006", "{1}参数不能为空");


    // 数据相关提示 编码 [3000,4000)
    ErrorCode GLOBAL_DATA_NOT_EXIST = new ErrorCode(PREFIX + "3000", "数据不存在");
    ErrorCode GLOBAL_DATA_PRESET_CANNOT_MODIFY = new ErrorCode(PREFIX + "3001", "预置数据不允许修改");
    ErrorCode GLOBAL_DATA_PRESET_CANNOT_DELETE = new ErrorCode(PREFIX + "3002", "预置数据不允许删除");


    ErrorCode GLOBAL_AUTH_ACCOUNT_ERROR = new ErrorCode(PREFIX + "400", "登陆账号错误");
    ErrorCode GLOBAL_AUTH_PASSWORD_ERROR = new ErrorCode(PREFIX + "400", "密码错误");

}
