package com.hzqing.orange.admin.module.system.permission.biz.service;

import com.hzqing.orange.admin.module.system.permission.common.vo.UserVO;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.UserAllQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.UserPageQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.UpdatePasswordRequest;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.UserAddRequest;
import com.hzqing.orange.admin.starter.common.vo.PageVO;

import java.util.List;

/**
 * @author 程序员橙子
 */
public interface UserService {


    /**
     * 根据登录账号查询用户
     *
     * @param username
     * @return
     */
    UserVO getByUsername(String username);

    /**
     * 根据用户ID查询用户
     *
     * @param userId 用户ID
     * @return 返回对应用户
     */
    UserVO getById(Long userId);

    Long add(UserAddRequest request);

    /**
     * 更新密码
     *
     * @param request
     * @return
     */
    Boolean updatePassword(UpdatePasswordRequest request);

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    PageVO<UserVO> page(UserPageQuery query);

    List<UserVO> queryAll(UserAllQuery query);
}
