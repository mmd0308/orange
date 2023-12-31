package cn.hengzq.orange.admin.module.system.permission.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "菜单和按钮资源ID")
public class ResourceIds {

    @Schema(description = "菜单ID")
    private List<Long> menuIds;

    @Schema(description = "按钮ID")
    private List<Long> buttonIds;
}
