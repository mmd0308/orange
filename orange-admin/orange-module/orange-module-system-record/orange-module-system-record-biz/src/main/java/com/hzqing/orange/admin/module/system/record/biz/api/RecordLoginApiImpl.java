package com.hzqing.orange.admin.module.system.record.biz.api;

import com.hzqing.orange.admin.module.system.record.api.RecordLoginApi;
import com.hzqing.orange.admin.module.system.record.biz.service.RecordLoginService;
import com.hzqing.orange.admin.module.system.record.common.vo.RecordLoginVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RecordLoginApiImpl implements RecordLoginApi {

    private final RecordLoginService recordLoginService;

    @Override
    public Long add(RecordLoginVO recordLoginVO) {
        return recordLoginService.add(recordLoginVO);
    }
}
