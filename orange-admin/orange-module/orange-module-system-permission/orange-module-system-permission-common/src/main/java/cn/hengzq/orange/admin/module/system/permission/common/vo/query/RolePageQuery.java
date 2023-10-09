package cn.hengzq.orange.admin.module.system.permission.common.vo.query;

import cn.hengzq.orange.admin.starter.common.constants.enums.support.CommonDataStatusEnum;
import cn.hengzq.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色分页查询")
@Data
public class RolePageQuery extends PageQuery {

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色名称,模糊查询")
    private String nameLike;

    @Schema(description = "角色权限编码")
    private String permission;

    @Schema(description = "权限编码,模糊匹配")
    private String permissionLike;

    @Schema(description = "状态")
    private CommonDataStatusEnum status;


}
