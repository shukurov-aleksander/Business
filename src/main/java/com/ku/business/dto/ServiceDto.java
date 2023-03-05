package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Service data transfer object")
@Accessors(chain = true)
public class ServiceDto {
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Service name", requiredMode = Schema.RequiredMode.REQUIRED, example = "Service")
    private String serviceName;
    @Schema(description = "Service sum", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long sum;
    @Schema(description = "Service description", requiredMode = Schema.RequiredMode.REQUIRED, example = "Description")
    private String serviceDescription;
}