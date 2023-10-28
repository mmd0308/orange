package cn.hengzq.orange.admin.module.system.file.biz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @author 程序员橙子
 */
@Data
@Builder
public class FileListQuery {

    private List<Long> ids;

    private LocalDateTime createdStartTime;

    private LocalDateTime createdEndTime;
}
