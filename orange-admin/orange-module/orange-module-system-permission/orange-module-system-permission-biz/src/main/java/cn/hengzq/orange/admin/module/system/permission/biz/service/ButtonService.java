package cn.hengzq.orange.admin.module.system.permission.biz.service;

import cn.hengzq.orange.admin.module.system.permission.common.vo.ButtonVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.ButtonAllQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.ButtonPageQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.ButtonAddRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.ButtonUpdateRequest;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;

import java.util.List;

/**
 * @author 程序员橙子
 */
public interface ButtonService {

    /**
     * 分页查询
     */
    PageVO<ButtonVO> page(ButtonPageQuery queryVo);

    /**
     * 新增按钮
     *
     * @param request 新增按钮参数
     * @return 返回新数据的ID
     */
    Long add(ButtonAddRequest request);

    /**
     * 根据ID更新
     */
    Boolean updateById(Long id, ButtonUpdateRequest request);

    List<ButtonVO> queryAll(ButtonAllQuery query);
}
