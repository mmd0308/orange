package cn.hengzq.orange.admin.module.system.file.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Schema(description = "文件配置存储")
public class FileStorageVO implements Serializable {

    @Schema(description = "存储类型Key")
    private String key;

    @Schema(description = "存储类型名称")
    private String name;

    public FileStorageVO(String key, String name) {
        this.key = key;
        this.name = name;
    }
}
