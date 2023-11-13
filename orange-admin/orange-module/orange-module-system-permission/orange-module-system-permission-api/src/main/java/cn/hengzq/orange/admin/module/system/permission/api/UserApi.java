package cn.hengzq.orange.admin.module.system.permission.api;

import cn.hengzq.orange.admin.module.system.permission.common.vo.UserVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 程序员橙子
 */
public interface UserApi {
    UserVO getByUsername(String username);


    List<UserVO> queryByIds(List<Long> ids);

    Map<Long, String> queryMapNameByIds(Set<Long> ids);
}
