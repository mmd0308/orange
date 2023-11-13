package cn.hengzq.orange.admin.module.system.info.common.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Schema(description = "消息管理-查询所有数据参数")
@Data
public class InfoAllQuery implements Serializable {

    @Schema(description = "ID 批量查询")
    private List<Long> ids;

    @Schema(description = "标题,模糊查询")
    private String titleLike;

    @Schema(description = "类型ID")
    private Long typeId;

    @Schema(description = "类型ID 批量查询")
    private List<Long> typeIds;
}
