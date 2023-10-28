package cn.hengzq.orange.admin.module.system.file.common.vo.query;

import cn.hengzq.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "文件配置管理-分页查询")
public class FileConfigPageQuery extends PageQuery {

    @Schema(description = "名称查询")
    private String nameLike;

}
