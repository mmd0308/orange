package cn.hengzq.orange.admin.module.system.permission.biz.dto;

import cn.hengzq.orange.admin.module.system.permission.common.enums.ResourceTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author 程序员橙子
 */
@Data
@Builder
public class RoleResourceRlListQuery {

    private Long roleId;

    private List<Long> roleIds;

    private ResourceTypeEnum resourceType;
}
