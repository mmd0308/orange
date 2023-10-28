package cn.hengzq.orange.admin.module.system.info.biz.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


/**
 * @author 程序员橙子
 */
@Data
@Builder
public class InfoTypeListQuery {

    private List<Long> ids;

    private String name;

    private String nameLike;

    private String code;

    private String codeLike;

    private Long parentId;


}
