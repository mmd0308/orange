package cn.hengzq.orange.admin.module.system.permission.biz.api;

import cn.hengzq.orange.admin.module.system.permission.api.UserApi;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.UserEntity;
import cn.hengzq.orange.admin.module.system.permission.biz.manager.UserManager;
import cn.hengzq.orange.admin.module.system.permission.biz.service.UserService;
import cn.hengzq.orange.admin.module.system.permission.common.vo.UserVO;
import cn.hengzq.orange.admin.starter.common.util.CollUtils;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 程序员橙子
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserApiImpl implements UserApi {

    private final UserManager manager;

    private final UserService service;

    @Override
    public UserVO getByUsername(String username) {
        return service.getByUsername(username);
    }

    @Override
    public List<UserVO> queryByIds(List<Long> ids) {
        return service.queryByIds(ids);
    }

    @Override
    public Map<Long, String> queryMapNameByIds(Set<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Map.of();
        }
        List<UserEntity> entityList = manager.listByIds(ids);
        if (CollUtil.isEmpty(entityList)) {
            return Map.of();
        }
        return CollUtils.convertMap(entityList, UserEntity::getId, UserEntity::getName);
    }
}
