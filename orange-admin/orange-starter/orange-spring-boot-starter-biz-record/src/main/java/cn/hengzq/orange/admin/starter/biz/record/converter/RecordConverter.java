package cn.hengzq.orange.admin.starter.biz.record.converter;

import cn.hengzq.orange.admin.starter.biz.record.dto.RecordDTO;
import cn.hengzq.orange.admin.module.system.record.common.vo.RecordLoginVO;
import cn.hengzq.orange.admin.module.system.record.common.vo.RecordOperationVO;
import cn.hengzq.orange.admin.starter.common.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * @author 程序员橙子
 */
@Mapper
public interface RecordConverter extends Converter {

    RecordConverter INSTANCE = Mappers.getMapper(RecordConverter.class);

    RecordOperationVO toRecordOperation(RecordDTO record);

    RecordLoginVO toRecordLogin(RecordDTO record);
}
