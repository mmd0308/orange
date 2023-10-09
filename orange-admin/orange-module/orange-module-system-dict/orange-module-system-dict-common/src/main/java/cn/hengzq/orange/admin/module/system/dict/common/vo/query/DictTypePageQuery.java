package cn.hengzq.orange.admin.module.system.dict.common.vo.query;

import cn.hengzq.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "字典类型-分页查询")
public class DictTypePageQuery extends PageQuery {

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典类型")
    private String dictType;
}
