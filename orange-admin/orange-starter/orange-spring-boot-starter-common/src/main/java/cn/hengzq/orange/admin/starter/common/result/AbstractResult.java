package cn.hengzq.orange.admin.starter.common.result;

import cn.hengzq.orange.admin.starter.common.constant.GlobalErrorCodeConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Slf4j
@Data
@Schema(description = "响应")
public abstract class AbstractResult implements Serializable {

    @Schema(description = "编码 200表示成功，其他值表示失败")
    protected String code = GlobalErrorCodeConstant.SUCCESS.getCode();

    @Schema(description = "消息内容")
    protected String msg = GlobalErrorCodeConstant.SUCCESS.getMsg();

    @Schema(description = "请求ID")
    protected String requestId;

}
