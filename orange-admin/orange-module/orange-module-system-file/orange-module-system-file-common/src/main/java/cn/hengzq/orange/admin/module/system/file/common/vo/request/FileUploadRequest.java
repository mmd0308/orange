package cn.hengzq.orange.admin.module.system.file.common.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@Schema(description = "文件上传参数")
public class FileUploadRequest implements Serializable {

    @Schema(description = "文件", requiredMode = Schema.RequiredMode.REQUIRED)
    private MultipartFile file;

    @Schema(description = "文件路径")
    private String path;

}
