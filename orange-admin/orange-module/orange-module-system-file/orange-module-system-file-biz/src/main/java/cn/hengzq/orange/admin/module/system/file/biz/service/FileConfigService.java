package cn.hengzq.orange.admin.module.system.file.biz.service;

import cn.hengzq.orange.admin.module.system.file.common.vo.FileConfigVO;
import cn.hengzq.orange.admin.module.system.file.common.vo.FileStorageVO;
import cn.hengzq.orange.admin.module.system.file.common.vo.query.FileConfigPageQuery;
import cn.hengzq.orange.admin.module.system.file.common.vo.request.FileConfigAddOrUpdateRequest;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;

import java.util.List;

/**
 * @author 程序员橙子
 */
public interface FileConfigService {


    /**
     * 新建配置文件
     *
     * @param addRequest 新增参数
     * @return 返回新增数据ID
     */
    Long add(FileConfigAddOrUpdateRequest addRequest);

    /**
     * 分页查询
     *
     * @param query 查询参数
     * @return 返回结果
     */
    PageVO<FileConfigVO> page(FileConfigPageQuery query);

    /**
     * 获取默认配置
     *
     * @return 返回默认配置
     */
    FileConfigVO getDefaultConfig();

    List<FileStorageVO> queryAllStorages();

    Boolean updateMaster(Long id);

    Boolean removeById(Long id);
}
