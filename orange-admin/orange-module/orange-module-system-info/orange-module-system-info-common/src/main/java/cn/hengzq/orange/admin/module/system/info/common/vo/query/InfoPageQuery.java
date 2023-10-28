package cn.hengzq.orange.admin.module.system.info.common.vo.query;

import cn.hengzq.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Schema(description = "消息管理-分页查询参数")
@Data
public class InfoPageQuery extends PageQuery {

    @Schema(description = "ID 批量查询")
    private List<Long> ids;

    @Schema(description = "标题,模糊查询")
    private String titleLike;

    @Schema(description = "类型ID")
    private Long typeId;

    @Schema(description = "类型ID 批量查询")
    private List<Long> typeIds;

}
