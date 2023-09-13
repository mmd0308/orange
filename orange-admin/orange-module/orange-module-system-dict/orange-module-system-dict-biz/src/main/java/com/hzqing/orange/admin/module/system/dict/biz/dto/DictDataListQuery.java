package com.hzqing.orange.admin.module.system.dict.biz.dto;

import com.hzqing.orange.admin.starter.common.constants.enums.support.DataPresetFlagEnum;
import lombok.Builder;
import lombok.Data;


/**
 * @author 程序员橙子
 */
@Data
@Builder
public class DictDataListQuery {

    private String dictType;


    private DataPresetFlagEnum presetFlag;

    /**
     * 字典数据标签
     */
    private String dictLabel;

    private String dictLabelLike;
}
