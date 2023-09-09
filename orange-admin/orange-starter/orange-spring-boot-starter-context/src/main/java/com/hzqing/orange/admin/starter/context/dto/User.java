package com.hzqing.orange.admin.starter.context.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class User implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 登陆账号
     */
    private String username;


    /**
     * 如果上下文中存在用户ID 不允许覆盖
     *
     * @param userId
     * @return
     */
    public User setUserId(Long userId) {
        if (this.userId == null) {
            this.userId = userId;
        }
        return this;
    }

    public User setUsername(String username) {
        if (this.username == null) {
            this.username = username;
        }
        return this;
    }

}
