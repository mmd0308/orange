package cn.hengzq.orange.admin.module.system.file.common.vo.query;

import cn.hengzq.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "文件管理-分页查询")
public class FilePageQuery extends PageQuery {

    @Schema(description = "创建开始时间 格式:yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdStartTime;

    @Schema(description = "创建结束时间 格式:yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdEndTime;
}
