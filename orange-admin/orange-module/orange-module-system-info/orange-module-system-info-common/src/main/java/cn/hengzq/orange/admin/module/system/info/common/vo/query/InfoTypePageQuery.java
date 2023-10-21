package cn.hengzq.orange.admin.module.system.info.common.vo.query;

import cn.hengzq.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Schema(description = "消息类型管理-分页查询参数")
@Data
public class InfoTypePageQuery extends PageQuery {

}
