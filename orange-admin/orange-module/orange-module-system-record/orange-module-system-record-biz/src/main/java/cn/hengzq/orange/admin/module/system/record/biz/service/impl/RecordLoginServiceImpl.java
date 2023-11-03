package cn.hengzq.orange.admin.module.system.record.biz.service.impl;

import cn.hengzq.orange.admin.module.system.permission.api.UserApi;
import cn.hengzq.orange.admin.module.system.record.biz.converter.RecordLoginConverter;
import cn.hengzq.orange.admin.module.system.record.biz.dto.RecordLoginListQuery;
import cn.hengzq.orange.admin.module.system.record.biz.entity.RecordLoginEntity;
import cn.hengzq.orange.admin.module.system.record.biz.manager.RecordLoginManager;
import cn.hengzq.orange.admin.module.system.record.biz.service.RecordLoginService;
import cn.hengzq.orange.admin.module.system.record.common.vo.RecordOperationVO;
import cn.hengzq.orange.admin.starter.common.util.CollUtils;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hengzq.orange.admin.module.system.record.common.vo.RecordLoginVO;
import cn.hengzq.orange.admin.module.system.record.common.vo.query.RecordLoginPageQuery;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author 程序员橙子
 */
@Slf4j
@Service
@AllArgsConstructor
public class RecordLoginServiceImpl implements RecordLoginService {

    private RecordLoginManager recordLoginManager;

    private final UserApi userApi;

    @Override
    public Long add(RecordLoginVO RecordLoginVO) {
        RecordLoginEntity entity = RecordLoginConverter.INSTANCE.toEntity(RecordLoginVO);
        return recordLoginManager.add(entity);
    }

    @Override
    public PageVO<RecordLoginVO> page(RecordLoginPageQuery query) {
        RecordLoginListQuery listQuery = RecordLoginConverter.INSTANCE.toListQuery(query);
        Page<RecordLoginEntity> page = recordLoginManager.page(query.getPageNo(), query.getPageSize(), listQuery);
        PageVO<RecordLoginVO> result = RecordLoginConverter.INSTANCE.toPage(page);
        List<RecordLoginVO> records = result.getRecords();
        if (CollUtil.isEmpty(records)) {
            return result;
        }
        Map<Long, String> userNameMap = userApi.queryMapNameByIds(CollUtils.convertSet(records, RecordLoginVO::getUserId));
        records.forEach(record -> {
            record.setUserName(userNameMap.get(record.getUserId()));
        });
        return result;
    }
}
