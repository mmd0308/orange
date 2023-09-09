package com.hzqing.orange.admin.module.system.permission.api;

import com.hzqing.orange.admin.module.system.permission.common.vo.UserVO;

/**
 * @author 程序员橙子
 */
public interface UserApi {
    UserVO getByUsername(String username);
}
