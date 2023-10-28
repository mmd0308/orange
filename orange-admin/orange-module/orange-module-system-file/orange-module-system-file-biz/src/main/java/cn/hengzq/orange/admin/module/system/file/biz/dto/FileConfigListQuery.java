package cn.hengzq.orange.admin.module.system.file.biz.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


/**
 * @author 程序员橙子
 */
@Data
@Builder
public class FileConfigListQuery {

    private List<Long> ids;

    private String name;

    private String nameLike;

    private Boolean master;

}
