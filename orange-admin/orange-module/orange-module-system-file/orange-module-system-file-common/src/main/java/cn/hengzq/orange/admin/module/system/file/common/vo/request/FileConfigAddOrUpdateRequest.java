package cn.hengzq.orange.admin.module.system.file.common.vo.request;

import cn.hengzq.orange.admin.module.system.file.common.exception.SystemFileErrorCode;
import cn.hengzq.starter.storage.constant.StorageEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "文件配置管理-新增或修改参数")
public class FileConfigAddOrUpdateRequest implements Serializable {

    @Schema(description = "存储器")
    @NotNull(message = SystemFileErrorCode.CONFIG_STORAGE_CANNOT_NULL_KEY)
    private StorageEnum storage;

    @Schema(description = "配置名称")
    @NotBlank(message = SystemFileErrorCode.CONFIG_NAME_CANNOT_NULL_KEY)
    private String name;

    @Schema(description = "配置")
    private Map<String, Object> configs;

    @Schema(description = "是否设置为主节点")
    private Boolean master;
}
