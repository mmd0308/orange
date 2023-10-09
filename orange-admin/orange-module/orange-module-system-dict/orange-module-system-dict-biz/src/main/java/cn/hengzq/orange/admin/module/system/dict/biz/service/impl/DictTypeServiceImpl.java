package cn.hengzq.orange.admin.module.system.dict.biz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hengzq.orange.admin.module.system.dict.biz.converter.DictTypeConverter;
import cn.hengzq.orange.admin.module.system.dict.biz.dto.DictDataListQuery;
import cn.hengzq.orange.admin.module.system.dict.biz.dto.DictTypeListQuery;
import cn.hengzq.orange.admin.module.system.dict.biz.entity.DictDataEntity;
import cn.hengzq.orange.admin.module.system.dict.biz.entity.DictTypeEntity;
import cn.hengzq.orange.admin.module.system.dict.biz.manager.DictDataManager;
import cn.hengzq.orange.admin.module.system.dict.biz.manager.DictTypeManager;
import cn.hengzq.orange.admin.module.system.dict.biz.service.DictTypeService;
import cn.hengzq.orange.admin.module.system.dict.common.constants.SystemDictErrorCode;
import cn.hengzq.orange.admin.module.system.dict.common.vo.DictTypeVO;
import cn.hengzq.orange.admin.module.system.dict.common.vo.query.DictTypeAllQuery;
import cn.hengzq.orange.admin.module.system.dict.common.vo.query.DictTypePageQuery;
import cn.hengzq.orange.admin.module.system.dict.common.vo.request.DictTypeAddRequest;
import cn.hengzq.orange.admin.module.system.dict.common.vo.request.DictTypeUpdateRequest;
import cn.hengzq.orange.admin.starter.common.exception.GlobalErrorCodeConstants;
import cn.hengzq.orange.admin.starter.common.validator.Assert;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
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
public class DictTypeServiceImpl implements DictTypeService {

    private final DictTypeManager dictTypeManager;

    private final DictDataManager dictDataManager;

    @Override
    public PageVO<DictTypeVO> page(DictTypePageQuery queryVo) {
        DictTypeListQuery listQuery = DictTypeConverter.INSTANCE.toListQuery(queryVo);
        Page<DictTypeEntity> page = dictTypeManager.page(queryVo.getPageNo(), queryVo.getPageSize(), listQuery);
        return DictTypeConverter.INSTANCE.toPage(page);
    }

    @Override
    public Long add(DictTypeAddRequest request) {
        List<DictTypeEntity> list = dictTypeManager.listByParams(DictTypeListQuery.builder().dictType(request.getDictType()).build());
        Assert.isEmpty(list, SystemDictErrorCode.DICT_TYPE_TYPE_ALREADY_EXIST);
        DictTypeEntity entity = DictTypeConverter.INSTANCE.toEntity(request);
        return dictTypeManager.add(entity);
    }

    @Override
    public Boolean updateById(Long id, DictTypeUpdateRequest request) {
        DictTypeEntity entity = dictTypeManager.getById(id);
        Assert.nonNull(entity, GlobalErrorCodeConstants.GLOBAL_DATA_NOT_EXIST);
        entity = DictTypeConverter.INSTANCE.updateConvert(entity, request);
        return dictTypeManager.updateById(entity);
    }

    @Override
    public Boolean removeById(Long id) {
        DictTypeEntity entity = dictTypeManager.getById(id);
        Assert.nonNull(entity, SystemDictErrorCode.GLOBAL_DATA_NOT_EXIST);
        List<DictDataEntity> entityList = dictDataManager.listByParams(DictDataListQuery.builder().dictType(entity.getDictType()).build());
        Assert.isEmpty(entityList, SystemDictErrorCode.DICT_TYPE_DELETE_ERROR_EXIST_DATA);
        return dictTypeManager.removeById(id);
    }

    @Override
    public List<DictTypeVO> queryAll(DictTypeAllQuery queryVo) {
        DictTypeListQuery listQuery = DictTypeConverter.INSTANCE.toListQuery(queryVo);
        List<DictTypeEntity> entityList = dictTypeManager.listByParams(listQuery);
        return DictTypeConverter.INSTANCE.toListVO(entityList);
    }
}
