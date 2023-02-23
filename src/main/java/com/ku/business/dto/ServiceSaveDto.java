package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;

@Data
@Schema(description = "Service save or update data transfer object")
@Accessors(chain = true)
public class ServiceSaveDto {
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Service name")
    private String serviceName;
    @Schema(description = "Service sum")
    @Min(1)
    private Long sum;
}

