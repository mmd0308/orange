package cn.hengzq.orange.admin.module.system.info.common.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Schema(description = "消息类型管理-查询所有数据参数")
@Data
public class InfoTypeAllQuery implements Serializable {


    @Schema(description = "类型名称,模糊查询")
    private String nameLike;
}
