package com.hzqing.orange.admin.starter.common.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "ID请求")
public class IdRequest implements Serializable {

    @NotNull
    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Serializable id;
}
