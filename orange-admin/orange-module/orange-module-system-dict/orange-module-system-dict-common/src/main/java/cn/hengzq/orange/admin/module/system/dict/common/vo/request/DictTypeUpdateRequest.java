package cn.hengzq.orange.admin.module.system.dict.common.vo.request;

import cn.hengzq.orange.admin.starter.common.enums.support.CommonDataEnableStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "字典类型-修改参数")
public class DictTypeUpdateRequest implements Serializable {

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "状态")
    private CommonDataEnableStatusEnum status;

    @Schema(description = "备注")
    private String remark;
}
