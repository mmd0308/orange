package cn.hengzq.orange.admin.module.system.info.biz.manager;

import cn.hengzq.orange.admin.module.system.info.biz.dto.InfoTypeListQuery;
import cn.hengzq.orange.admin.module.system.info.biz.entity.InfoTypeEntity;
import cn.hengzq.orange.admin.starter.mybatis.manager.BaseManager;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * @author 程序员橙子
 */
public interface InfoTypeManager extends BaseManager<InfoTypeEntity> {

    Page<InfoTypeEntity> page(Integer pageNo, Integer pageSize, InfoTypeListQuery query);

    List<InfoTypeEntity> listByParams(InfoTypeListQuery query);
}
