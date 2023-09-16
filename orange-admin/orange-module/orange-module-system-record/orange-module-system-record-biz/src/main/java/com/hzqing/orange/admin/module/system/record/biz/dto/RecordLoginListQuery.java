package com.hzqing.orange.admin.module.system.record.biz.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RecordLoginListQuery {

    private String account;

    private LocalDateTime startLoginTime;

    private LocalDateTime endLoginTime;
}
