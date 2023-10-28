package cn.hengzq.orange.admin.module.system.file.common.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "文件配置管理-查询所有的参数")
public class FileConfigAllQuery implements Serializable {

    @Schema(description = "名称查询")
    private String nameLike;

}
