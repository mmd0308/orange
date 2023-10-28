package cn.hengzq.orange.admin.module.system.file.common.vo;

import cn.hengzq.orange.admin.starter.common.vo.BaseTenantVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "文件")
public class FileVO extends BaseTenantVO {

    @Schema(description = "主键", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "配置ID")
    private Long configId;

    @Schema(description = "文件原名称")
    private String originalName;

    @Schema(description = "文件新名称")
    private String name;

    @Schema(description = "文件路径")
    private String path;

    @Schema(description = "文件类型")
    private String type;

    @Schema(description = "文件大小")
    private String size;

    @Schema(description = "文件URL")
    private String url;

}
