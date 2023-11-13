package cn.hengzq.orange.admin.module.system.info.common.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 消息管理
 *
 * @author 程序员橙子
 */
@Schema(description = "信息管理")
@Data
public class InfoAddOrUpdateRequest implements Serializable {

    @Schema(description = "typeId")
    private Long typeId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "排序")
    private Integer sort;

}
