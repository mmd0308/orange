package cn.hengzq.orange.admin.module.system.record.biz.manager.impl;

import cn.hengzq.orange.admin.module.system.record.biz.entity.RecordOperationEntity;
import cn.hengzq.orange.admin.module.system.record.biz.manager.RecordOperationManager;
import cn.hengzq.orange.admin.module.system.record.biz.mapper.RecordOperationMapper;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hengzq.orange.admin.module.system.record.common.vo.query.OperationRecordPageQuery;
import cn.hengzq.orange.admin.starter.mybatis.manager.impl.BaseManagerImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 * @author 程序员橙子
 */
@Slf4j
@Service
@AllArgsConstructor
public class RecordOperationManagerImpl extends BaseManagerImpl<RecordOperationMapper, RecordOperationEntity>
        implements RecordOperationManager {

    private final RecordOperationMapper mapper;

    @Override
    public RecordOperationMapper getMapper() {
        return this.mapper;
    }

    @Override
    public void removeAll() {
        mapper.delete(Wrappers.emptyWrapper());
    }

    @Override
    public Page<RecordOperationEntity> page(OperationRecordPageQuery query) {
        LambdaQueryWrapper<RecordOperationEntity> queryWrapper = Wrappers.<RecordOperationEntity>lambdaQuery()
                .eq(StrUtil.isNotBlank(query.getTraceId()), RecordOperationEntity::getTraceId, query.getTraceId())
                .eq(Objects.nonNull(query.getStatus()), RecordOperationEntity::getStatus, query.getStatus())
                .ge(Objects.nonNull(query.getOperationStartTime()), RecordOperationEntity::getStartTime, query.getOperationStartTime())
                .le(Objects.nonNull(query.getOperationEndTime()), RecordOperationEntity::getStartTime, query.getOperationEndTime())
                .orderByDesc(RecordOperationEntity::getStartTime);
        return mapper.selectPage(new Page<>(query.getPageNo(), query.getPageSize()), queryWrapper);
    }

//    @Override
//    public List<OperationRecord> queryExportList(OperationRecordExportQuery query) {
//        LambdaQueryWrapper<RecordOperationEntity> queryWrapper = getQueryWrapper(query.getModule(), query.getTraceId(), query.getOperationTime());
//        return converter.toListVo(mapper.selectList(queryWrapper));
//    }
}
