package cn.hengzq.orange.admin.starter.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 程序员橙子
 */
@Data
public class BaseVO implements Serializable {

    @Schema(description = "创建人ID", hidden = true, accessMode = Schema.AccessMode.READ_ONLY)
    private Long createdBy;

    @Schema(description = "创建时间", hidden = true, accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @Schema(description = "更新人ID", hidden = true, accessMode = Schema.AccessMode.READ_ONLY)
    private Long updatedBy;

    @Schema(description = "更新时间", hidden = true, accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;

}
