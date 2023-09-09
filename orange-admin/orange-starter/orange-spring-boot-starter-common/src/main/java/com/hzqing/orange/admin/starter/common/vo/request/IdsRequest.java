package com.hzqing.orange.admin.starter.common.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "IDS 请求")
public class IdsRequest implements Serializable {

    @NotEmpty
    @Schema(description = "IDS", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Serializable> ids;

}
