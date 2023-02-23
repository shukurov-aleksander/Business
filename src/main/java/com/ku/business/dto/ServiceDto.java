package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;

@Data
@Schema(description = "Service data transfer object")
@Accessors(chain = true)
public class ServiceDto {
    @Schema(description = "Uniq identification of the service data transfer object")
    private Long id;
    @Schema(description = "Name of the service")
    private String serviceName;
    @Schema(description = "Sum of the service")
    @Min(1)
    private Long sum;
    @Schema(description = "Service description")
    private String serviceDescription;
}
