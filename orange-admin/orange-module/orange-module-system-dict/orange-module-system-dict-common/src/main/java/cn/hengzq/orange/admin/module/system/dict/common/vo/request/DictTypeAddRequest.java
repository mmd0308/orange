package cn.hengzq.orange.admin.module.system.dict.common.vo.request;

import cn.hengzq.orange.admin.module.system.dict.common.constants.SystemDictErrorCode;
import cn.hengzq.orange.admin.starter.common.enums.support.CommonDataStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "字典类型-新增参数")
public class DictTypeAddRequest implements Serializable {

    @NotNull(message = SystemDictErrorCode.DICT_TYPE_NAME_CANNOT_NULL_KEY)
    @Schema(description = "字典名称")
    private String name;

    @NotNull(message = SystemDictErrorCode.DICT_TYPE_TYPE_CANNOT_NULL_KEY)
    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "状态")
    private CommonDataStatusEnum status;

    @Schema(description = "备注")
    private String remark;
}
