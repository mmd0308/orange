package cn.hengzq.orange.admin.module.system.permission.common.vo.query;

import cn.hengzq.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Schema(description = "按钮管理-查询所有数据参数")
@Data
public class ButtonAllQuery implements Serializable {

    @Schema(description = "菜单ID")
    private Long menuId;
}
