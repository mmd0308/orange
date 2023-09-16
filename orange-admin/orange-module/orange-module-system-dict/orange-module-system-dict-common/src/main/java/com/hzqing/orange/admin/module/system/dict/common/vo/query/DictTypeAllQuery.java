package com.hzqing.orange.admin.module.system.dict.common.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "字典类型-查询所有数据")
public class DictTypeAllQuery implements Serializable {

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典类型")
    private String dictType;
}
