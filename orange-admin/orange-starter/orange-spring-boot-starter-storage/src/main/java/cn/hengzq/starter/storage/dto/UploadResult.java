package cn.hengzq.starter.storage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @author 程序员橙子
 */
@Data
@Builder
public class UploadResult {

    @Schema(description = "文件名称")
    private String name;

    @Schema(description = "存储路径，相对路径")
    private String path;

    @Schema(description = "文件请求路径 URL")
    private String url;


}
