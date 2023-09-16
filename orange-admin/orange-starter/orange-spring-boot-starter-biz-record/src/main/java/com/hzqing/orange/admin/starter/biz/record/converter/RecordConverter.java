package com.hzqing.orange.admin.starter.biz.record.converter;

import com.hzqing.orange.admin.module.system.record.common.vo.RecordOperationVO;
import com.hzqing.orange.admin.starter.biz.record.dto.RecordDTO;
import com.hzqing.orange.admin.starter.common.converter.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * @author 程序员橙子
 */
@Mapper
public interface RecordConverter extends Converter {

    RecordConverter INSTANCE = Mappers.getMapper(RecordConverter.class);

    RecordOperationVO toRecordOperation(RecordDTO record);
}