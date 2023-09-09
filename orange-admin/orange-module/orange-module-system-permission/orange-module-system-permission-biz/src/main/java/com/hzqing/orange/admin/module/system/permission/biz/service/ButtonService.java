package com.hzqing.orange.admin.module.system.permission.biz.service;

import com.hzqing.orange.admin.module.system.permission.common.vo.Button;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.ButtonPageQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.ButtonAddRequest;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.ButtonUpdateRequest;
import com.hzqing.orange.admin.starter.common.vo.PageVO;

/**
 *@author 程序员橙子
 */
public interface ButtonService {

    /**
     * 分页查询
     *
     * @param queryVo
     * @return
     */
    PageVO<Button> page(ButtonPageQuery queryVo);

    /**
     * 新增按钮
     *
     * @param request 新增按钮参数
     * @return 返回新数据的ID
     */
    Long add(ButtonAddRequest request);

    /**
     * 根据ID更新
     *
     * @param id
     * @param request
     * @return
     */
    Boolean updateById(Long id, ButtonUpdateRequest request);
}
