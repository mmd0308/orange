package cn.hengzq.orange.admin.module.system.dict.common.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "字典数据-全部查询参数")
public class DictDataAllQuery implements Serializable {

    /**
     * 字典类型
     */
    @Schema(description = "字典类型")
    private List<String> dictTypes;


}
