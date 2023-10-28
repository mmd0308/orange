package cn.hengzq.orange.admin.module.system.file.common.vo;

import cn.hengzq.orange.admin.starter.common.vo.BaseTenantVO;
import cn.hengzq.starter.storage.constant.StorageEnum;
import cn.hengzq.starter.storage.core.StorageConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "文件配置管理")
public class FileConfigVO extends BaseTenantVO {

    @Schema(description = "主键", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "存储方式")
    private StorageEnum storage;

    @Schema(description = "配置名称")
    private String name;

    @Schema(description = "配置")
    private StorageConfig config;

    @Schema(description = "是否为主节点")
    private Boolean master;
}
