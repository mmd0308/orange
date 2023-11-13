package cn.hengzq.orange.admin.module.system.permission.biz.service;

import cn.hengzq.orange.admin.module.system.permission.common.vo.UserDetailsVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.UserVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.UserAllQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.UserPageQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.ResetPasswordRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.UpdatePasswordRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.UserAddRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.UserUpdateRequest;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;

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
     */
    Boolean updatePassword(UpdatePasswordRequest request);

    /**
     * 重置密码
     */
    Boolean resetPassword(ResetPasswordRequest request);

    /**
     * 分页查询
     */
    PageVO<UserVO> page(UserPageQuery query);

    List<UserVO> queryAll(UserAllQuery query);


    UserDetailsVO getDetailsById(Long id);

    Boolean updateById(Long id, UserUpdateRequest request);

    List<UserVO> queryByIds(List<Long> ids);
}
