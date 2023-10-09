package cn.hengzq.orange.admin.starter.common.vo.query;

import cn.hengzq.orange.admin.starter.common.constant.PageConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Data
public class PageQuery implements Serializable {

    @Schema(description = "查询页码，从1开始 默认值:1", defaultValue = "1")
    @Min(value = 1, message = "页码最小值为 1")
    private Integer pageNo = PageConstant.PAGE_NO;

    @Schema(description = "每页数量,取值范围[5,1000] 默认值:10", defaultValue = "10")
    @Min(value = 5, message = "每页条数最小值为 5")
    @Max(value = 1000, message = "每页条数最大值为 1000")
    private Integer pageSize = PageConstant.PAGE_SIZE;

}
