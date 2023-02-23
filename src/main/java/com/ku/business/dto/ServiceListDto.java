package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Service list data transfer object for list of the companies")
@Accessors(chain = true)
public class ServiceListDto {
    @Schema(description = "Uniq identification of the service data transfer object")
    private Long id;
    @Schema(description = "Name of the service")
    private String serviceName;
}
