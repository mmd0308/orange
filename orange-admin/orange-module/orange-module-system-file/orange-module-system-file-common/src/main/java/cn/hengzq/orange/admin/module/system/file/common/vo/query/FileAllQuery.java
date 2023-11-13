package cn.hengzq.orange.admin.module.system.file.common.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "文件管理-查询所有的参数")
public class FileAllQuery implements Serializable {

    @Schema(description = "名称查询")
    private String nameLike;

}
