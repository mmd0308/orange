package cn.hengzq.orange.admin.module.system.info.biz.dto;

import lombok.Builder;
import lombok.Data;


/**
 * @author 程序员橙子
 */
@Data
@Builder
public class InfoTypeListQuery {

    private String name;


    private String nameLike;

}
