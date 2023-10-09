package cn.hengzq.orange.admin.module.system.dict.biz.dto;

import lombok.Builder;
import lombok.Data;


/**
 * @author 程序员橙子
 */
@Data
@Builder
public class DictTypeListQuery {

    private String name;

    private String nameLike;


    private String dictType;

    private String dictTypeLike;

}
