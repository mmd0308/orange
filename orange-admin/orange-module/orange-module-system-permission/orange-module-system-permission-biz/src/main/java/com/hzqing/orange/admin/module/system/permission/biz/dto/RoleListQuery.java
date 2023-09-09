package com.hzqing.orange.admin.module.system.permission.biz.dto;

import com.hzqing.orange.admin.starter.common.constants.enums.support.CommonDataStatusEnum;
import lombok.Builder;
import lombok.Data;

/**
 *@author 程序员橙子
 */
@Data
@Builder
public class RoleListQuery {

    private String name;

    private String nameLike;

    private String permission;

    private String permissionLike;

    private CommonDataStatusEnum status;

}
