package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Service save or update data transfer object")
@Accessors(chain = true)
public class ServiceSaveDto {
    @Schema(description = "Id", required = true, example = "1")
    private Long id;
    @Schema(description = "Service name", required = true, example = "Service")
    private String serviceName;
    @Schema(description = "Service sum", required = true, example = "1")
    private Long sum;
}

