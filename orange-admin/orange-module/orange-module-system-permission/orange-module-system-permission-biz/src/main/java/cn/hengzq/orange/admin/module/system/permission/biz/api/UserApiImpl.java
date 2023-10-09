package cn.hengzq.orange.admin.module.system.permission.biz.api;

import cn.hengzq.orange.admin.module.system.permission.api.UserApi;
import cn.hengzq.orange.admin.module.system.permission.biz.service.UserService;
import cn.hengzq.orange.admin.module.system.permission.common.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 程序员橙子
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserApiImpl implements UserApi {

    private final UserService userService;

    @Override
    public UserVO getByUsername(String username) {
        return userService.getByUsername(username);
    }
}
