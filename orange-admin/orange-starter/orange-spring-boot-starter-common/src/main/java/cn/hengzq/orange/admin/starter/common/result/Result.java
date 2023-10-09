package cn.hengzq.orange.admin.starter.common.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 程序员橙子
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@Schema(description = "响应结果")
public class Result<T> extends AbstractResult {

    @Schema(description = "响应的数据")
    private T data;

}
