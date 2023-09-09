package com.hzqing.orange.admin.module.system.permission.biz.dto;

import com.hzqing.orange.admin.module.system.permission.common.constants.enums.ResourceTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 *@author 程序员橙子
 */
@Data
@Builder
public class RoleResourceRlQueryDTO {

    private Long roleId;

    private List<Long> roleIds;

    private ResourceTypeEnum resourceType;
}
