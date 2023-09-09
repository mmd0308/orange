package com.hzqing.orange.admin.module.system.permission.common.vo.query;


import com.hzqing.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "菜单分页查询")
@Data
public class MenuPageQuery extends PageQuery {

}
