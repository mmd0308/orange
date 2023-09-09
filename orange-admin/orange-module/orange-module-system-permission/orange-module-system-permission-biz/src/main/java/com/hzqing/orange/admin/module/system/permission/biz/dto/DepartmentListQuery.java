package com.hzqing.orange.admin.module.system.permission.biz.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 *@author 程序员橙子
 */
@Data
@Builder
public class DepartmentListQuery {

    private List<Long> ids;

    private Long parentId;

    private String name;

    private String nameLike;

    private String ancestorsLikeRight;
}
