package com.hzqing.orange.admin.module.system.record.biz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.orange.admin.module.system.record.biz.converter.RecordLoginConverter;
import com.hzqing.orange.admin.module.system.record.biz.dto.RecordLoginListQuery;
import com.hzqing.orange.admin.module.system.record.biz.entity.RecordLoginEntity;
import com.hzqing.orange.admin.module.system.record.biz.manager.RecordLoginManager;
import com.hzqing.orange.admin.module.system.record.biz.service.RecordLoginService;
import com.hzqing.orange.admin.module.system.record.common.vo.RecordLoginVO;
import com.hzqing.orange.admin.module.system.record.common.vo.query.RecordLoginPageQuery;
import com.hzqing.orange.admin.starter.common.vo.PageVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author 程序员橙子
 */
@Slf4j
@Service
@AllArgsConstructor
public class RecordLoginServiceImpl implements RecordLoginService {

    private RecordLoginManager recordLoginManager;

    @Override
    public Long add(RecordLoginVO RecordLoginVO) {
        RecordLoginEntity entity = RecordLoginConverter.INSTANCE.toEntity(RecordLoginVO);
        return recordLoginManager.add(entity);
    }

    @Override
    public PageVO<RecordLoginVO> page(RecordLoginPageQuery query) {
        RecordLoginListQuery listQuery = RecordLoginConverter.INSTANCE.toListQuery(query);
        Page<RecordLoginEntity> page = recordLoginManager.page(query.getPageNo(), query.getPageSize(), listQuery);
        return RecordLoginConverter.INSTANCE.toPage(page);
    }
}
