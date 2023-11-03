package cn.hengzq.orange.admin.module.system.record.biz.service.impl;

import cn.hengzq.orange.admin.module.system.permission.api.UserApi;
import cn.hengzq.orange.admin.module.system.record.biz.converter.RecordOperationConverter;
import cn.hengzq.orange.admin.module.system.record.biz.dto.RecordOperationListQuery;
import cn.hengzq.orange.admin.module.system.record.biz.entity.RecordOperationEntity;
import cn.hengzq.orange.admin.module.system.record.biz.manager.RecordOperationManager;
import cn.hengzq.orange.admin.module.system.record.biz.service.RecordOperationService;
import cn.hengzq.orange.admin.module.system.record.common.vo.RecordOperationVO;
import cn.hengzq.orange.admin.module.system.record.common.vo.query.OperationRecordExportQuery;
import cn.hengzq.orange.admin.module.system.record.common.vo.query.OperationRecordPageQuery;
import cn.hengzq.orange.admin.starter.common.util.CollUtils;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


/**
 * @author 程序员橙子
 */
@Slf4j
@Service
@AllArgsConstructor
public class RecordOperationServiceImpl implements RecordOperationService {

    private final RecordOperationManager manager;

    private final UserApi userApi;

    @Override
    public List<RecordOperationVO> queryExportList(OperationRecordExportQuery query) {
        return null;
    }

    @Override
    public Long add(RecordOperationVO recordOperationVO) {
        RecordOperationEntity entity = RecordOperationConverter.INSTANCE.toEntity(recordOperationVO);
        return manager.add(entity);
    }

    @Override
    public PageVO<RecordOperationVO> page(OperationRecordPageQuery queryVo) {
        RecordOperationListQuery listQuery = RecordOperationConverter.INSTANCE.toListQuery(queryVo);
        Page<RecordOperationEntity> page = manager.page(queryVo.getPageNo(), queryVo.getPageSize(), listQuery);
        PageVO<RecordOperationVO> result = RecordOperationConverter.INSTANCE.toPage(page);
        List<RecordOperationVO> records = result.getRecords();
        if (CollUtil.isEmpty(records)) {
            return result;
        }
        Set<Long> userIds = CollUtils.convertSet(records, RecordOperationVO::getUserId);
        Map<Long, String> userNameMap = userApi.queryMapNameByIds(userIds);
        records.forEach(record -> {
            record.setUserName(userNameMap.get(record.getUserId()));
        });
        return result;
    }

    @Override
    public RecordOperationVO getById(Long id) {
        RecordOperationEntity entity = manager.getById(id);
        if (Objects.isNull(entity)) {
            return null;
        }
        RecordOperationVO vo = RecordOperationConverter.INSTANCE.toVo(entity);
        Map<Long, String> userNameMap = userApi.queryMapNameByIds(Set.of(entity.getUserId()));
        vo.setUserName(userNameMap.get(vo.getUserId()));
        return vo;
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
