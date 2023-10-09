package cn.hengzq.orange.admin.module.system.permission.common.vo.query;

import cn.hengzq.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Schema(description = "按钮分页查询")
@Data
public class ButtonPageQuery extends PageQuery {

    @Schema(description = "菜单ID")
    private Long menuId;
}
