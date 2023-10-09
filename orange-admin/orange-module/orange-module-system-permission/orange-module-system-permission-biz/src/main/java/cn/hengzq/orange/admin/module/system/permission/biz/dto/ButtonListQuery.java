package cn.hengzq.orange.admin.module.system.permission.biz.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


/**
 * @author 程序员橙子
 */
@Data
@Builder
public class ButtonListQuery {

    private Long menuId;

    private List<Long> menuIds;

    private String permission;

    private String permissionLike;
}
