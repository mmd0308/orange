package com.hzqing.orange.admin.module.system.permission.biz.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


/**
 * @author 程序员橙子
 */
@Data
@Builder
public class UserListQuery {

    private Long departmentId;

    private List<Long> departmentIds;

    private String name;

    private String nameLike;

    private String email;

    private String emailLike;

    private String username;

    private String usernameLike;

    private String phone;

    private String phoneLike;
}
