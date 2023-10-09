package cn.hengzq.orange.admin.module.system.permission.biz.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.RoleEntity;
import cn.hengzq.orange.admin.starter.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author 程序员橙子
 */
@Repository
public interface RoleMapper extends BaseMapper<RoleEntity> {

    /**
     * https://baomidou.com/pages/2976a3/#%E6%8B%A6%E6%88%AA%E5%BF%BD%E7%95%A5%E6%B3%A8%E8%A7%A3-interceptorignore
     *
     * @param userId 用户ID
     * @return 返回用户角色
     */
    @InterceptorIgnore(tenantLine = "true")
    List<RoleEntity> selectListByUserId(Long userId);
}
