package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;

@Data
@Schema(description = "Content save or update data transfer object")
@Accessors(chain = true)
public class ContentSaveDto {
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Content quantity")
    @Min(1)
    private Long quantity;
    @Schema(description = "Content service")
    private ServiceDto service;
}
