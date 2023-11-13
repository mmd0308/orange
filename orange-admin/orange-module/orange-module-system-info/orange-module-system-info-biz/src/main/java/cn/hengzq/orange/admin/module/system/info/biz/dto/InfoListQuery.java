package cn.hengzq.orange.admin.module.system.info.biz.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


/**
 * @author 程序员橙子
 */
@Data
@Builder
public class InfoListQuery {
    private String titleLike;

    private List<Long> ids;

    private Long typeId;

    private List<Long> typeIds;

}
