package com.hzqing.orange.admin.module.system.permission.common.vo.query;

import com.hzqing.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "登录分页查询参数")
public class LoginRecordPageQuery extends PageQuery {

    @Schema(description = "登录账号")
    private String account;

    @Schema(description = "开始时间 第一个为开始时间 第二个为结束时间")
    private List<LocalDateTime> loginTime;

}
