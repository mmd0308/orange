package com.hzqing.orange.admin.module.system.permission.biz.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.orange.admin.module.system.permission.biz.dto.ButtonListQuery;
import com.hzqing.orange.admin.module.system.permission.biz.entity.ButtonEntity;
import com.hzqing.orange.admin.starter.mybatis.manager.BaseManager;

import java.util.List;


/**
 * @author 程序员橙子
 */
public interface ButtonManager extends BaseManager<ButtonEntity> {

    Page<ButtonEntity> page(Integer pageNo, Integer pageSize, ButtonListQuery query);

    List<ButtonEntity> listByParams(ButtonListQuery query);
}
