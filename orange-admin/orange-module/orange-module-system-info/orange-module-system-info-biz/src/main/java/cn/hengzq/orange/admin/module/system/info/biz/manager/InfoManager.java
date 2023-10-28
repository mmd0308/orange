package cn.hengzq.orange.admin.module.system.info.biz.manager;

import cn.hengzq.orange.admin.module.system.info.biz.dto.InfoListQuery;
import cn.hengzq.orange.admin.module.system.info.biz.entity.InfoEntity;
import cn.hengzq.orange.admin.starter.mybatis.manager.BaseManager;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * @author 程序员橙子
 */
public interface InfoManager extends BaseManager<InfoEntity> {

    Page<InfoEntity> page(Integer pageNo, Integer pageSize, InfoListQuery query);

    List<InfoEntity> listByParams(InfoListQuery query);
}
