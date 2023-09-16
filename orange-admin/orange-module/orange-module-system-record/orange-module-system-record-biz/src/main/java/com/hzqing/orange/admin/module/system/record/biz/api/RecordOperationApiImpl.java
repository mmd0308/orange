package com.hzqing.orange.admin.module.system.record.biz.api;

import com.hzqing.orange.admin.module.system.record.api.RecordOperationApi;
import com.hzqing.orange.admin.module.system.record.biz.service.RecordOperationService;
import com.hzqing.orange.admin.module.system.record.common.vo.RecordOperationVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RecordOperationApiImpl implements RecordOperationApi {

    private final RecordOperationService recordOperationService;

    @Override
    public Long add(RecordOperationVO recordOperationVO) {
        return recordOperationService.add(recordOperationVO);
    }
}
