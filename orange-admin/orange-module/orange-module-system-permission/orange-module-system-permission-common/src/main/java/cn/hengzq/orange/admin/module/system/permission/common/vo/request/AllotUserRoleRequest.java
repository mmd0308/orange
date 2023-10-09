package cn.hengzq.orange.admin.module.system.permission.common.vo.request;

import cn.hengzq.orange.admin.starter.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author 程序员橙子
 */
@Data
public class AllotUserRoleRequest extends BaseVO {

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "用户ID不能为空.")
    private Long userId;

    @Schema(description = "角色Id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "角色ID不能为空")
    private List<Long> roleIds;


}