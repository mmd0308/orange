package cn.hengzq.orange.admin.module.system.permission.biz.service;


import cn.hengzq.orange.admin.module.system.permission.common.vo.Token;
import cn.hengzq.orange.admin.module.system.permission.common.vo.UserInfo;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.LoginRequest;

/**
 *@author 程序员橙子
 */
public interface AuthService {

    /**
     * 登录
     *
     * @param request
     * @return
     */
    Token login(LoginRequest request);

    /**
     * 获取登录当前用户信息
     *
     * @return
     */
    UserInfo getInfo();

    /**
     * 密码加密
     *
     * @param password 明文密码
     * @return 加密后密码
     */
    String passwordEncrypt(String password);
}
