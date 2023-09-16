package com.hzqing.orange.admin.module.system.record.biz.service.impl;

import com.hzqing.orange.admin.module.system.record.biz.converter.RecordOperationConverter;
import com.hzqing.orange.admin.module.system.record.biz.entity.RecordOperationEntity;
import com.hzqing.orange.admin.module.system.record.biz.manager.RecordOperationManager;
import com.hzqing.orange.admin.module.system.record.biz.service.RecordOperationService;
import com.hzqing.orange.admin.module.system.record.common.vo.RecordOperationVO;
import com.hzqing.orange.admin.module.system.record.common.vo.query.OperationRecordExportQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author 程序员橙子
 */
@Slf4j
@Service
@AllArgsConstructor
public class RecordOperationServiceImpl implements RecordOperationService {

    private final RecordOperationManager recordOperationManager;

    @Override
    public List<RecordOperationVO> queryExportList(OperationRecordExportQuery query) {
        return null;
    }

    @Override
    public Long add(RecordOperationVO recordOperationVO) {
        RecordOperationEntity entity = RecordOperationConverter.INSTANCE.toEntity(recordOperationVO);
        return recordOperationManager.add(entity);
    }

//    @Override
//    public List<OperationRecord> queryExportList(OperationRecordExportQuery query) {
//        LambdaQueryWrapper<RecordOperationEntity> queryWrapper = getQueryWrapper(query.getModule(), query.getTraceId(), query.getOperationTime());
//        return converter.toListVo(mapper.selectList(queryWrapper));
//    }
//
//    private LambdaQueryWrapper<RecordOperationEntity> getQueryWrapper(String module, String traceId, List<LocalDateTime> operationTimes) {
//        LambdaQueryWrapper<RecordOperationEntity> queryWrapper = Wrappers.<RecordOperationEntity>lambdaQuery()
//                .like(StrUtil.isNotBlank(module), RecordOperationEntity::getModule, module)
//                .eq(StrUtil.isNotBlank(traceId), RecordOperationEntity::getTraceId, traceId);
//
//        if (CollUtil.isNotEmpty(operationTimes) && operationTimes.size() == 2) {
//            long startTime = operationTimes.get(0).toInstant(ZoneOffset.of("+8")).toEpochMilli();
//            long endTime = operationTimes.get(1).toInstant(ZoneOffset.of("+8")).toEpochMilli();
//            queryWrapper.between(RecordOperationEntity::getStartTime, startTime, endTime);
//        }
//        return queryWrapper;
//    }
}
