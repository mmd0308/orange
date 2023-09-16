package com.hzqing.orange.admin.module.system.permission.biz.dto;

import lombok.Builder;
import lombok.Data;


/**
 *@author 程序员橙子
 */
@Data
@Builder
public class MenuListQuery {
    private Long parentId;

    private String name;

    private String permission;

    private String permissionLike;

}
