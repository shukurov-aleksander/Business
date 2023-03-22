package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Service list data transfer object for list of the companies")
@Accessors(chain = true)
public class ServiceListDto {
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Service name", requiredMode = Schema.RequiredMode.REQUIRED, example = "Service")
    private String serviceName;
}