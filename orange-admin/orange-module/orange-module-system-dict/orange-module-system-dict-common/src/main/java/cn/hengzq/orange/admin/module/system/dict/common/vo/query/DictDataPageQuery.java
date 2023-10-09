package cn.hengzq.orange.admin.module.system.dict.common.vo.query;

import cn.hengzq.orange.admin.starter.common.enums.support.DataPresetFlagEnum;
import cn.hengzq.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "字典数据-分页查询")
public class DictDataPageQuery extends PageQuery {

    /**
     * 字典类型
     */
    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "预设标记")
    private DataPresetFlagEnum presetFlag;

    /**
     * 字典数据标签
     */
    @Schema(description = "字典标签")
    private String dictLabel;
}
