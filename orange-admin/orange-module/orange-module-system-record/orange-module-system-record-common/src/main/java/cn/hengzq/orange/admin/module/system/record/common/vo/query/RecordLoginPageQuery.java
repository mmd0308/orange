package cn.hengzq.orange.admin.module.system.record.common.vo.query;

import cn.hengzq.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "登录记录-分页查询")
public class RecordLoginPageQuery extends PageQuery {

    @Schema(description = "登录账号")
    private String account;

    @Schema(description = "开始时间 第一个为开始时间 第二个为结束时间")
    private List<LocalDateTime> loginTime;

    @Schema(description = "登录时间-开始时间")
    private LocalDateTime startLoginTime;

    @Schema(description = "登录时间-结束时间")
    private LocalDateTime endLoginTime;

}
