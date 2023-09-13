package com.hzqing.orange.admin.module.system.dict.biz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.orange.admin.module.system.dict.biz.converter.DictDataConverter;
import com.hzqing.orange.admin.module.system.dict.biz.dto.DictDataListQuery;
import com.hzqing.orange.admin.module.system.dict.biz.entity.DictDataEntity;
import com.hzqing.orange.admin.module.system.dict.biz.manager.DictDataManager;
import com.hzqing.orange.admin.module.system.dict.biz.service.DictDataService;
import com.hzqing.orange.admin.module.system.dict.common.vo.DictDataVO;
import com.hzqing.orange.admin.module.system.dict.common.vo.query.DictDataPageQuery;
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
public class DictDataServiceImpl implements DictDataService {

    private final DictDataManager dictDataManager;

    @Override
    public PageVO<DictDataVO> page(DictDataPageQuery query) {
        DictDataListQuery listQuery = DictDataConverter.INSTANCE.toListQuery(query);
        Page<DictDataEntity> page = dictDataManager.page(query.getPageNo(), query.getPageSize(), listQuery);
        return DictDataConverter.INSTANCE.toPage(page);
    }
}
