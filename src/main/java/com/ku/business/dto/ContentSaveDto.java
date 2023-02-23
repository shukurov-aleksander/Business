package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;

@Data
@Schema(description = "Content save or update data transfer object")
@Accessors(chain = true)
public class ContentSaveDto {
    @Schema(description = "Uniq identification of the content data transfer object")
    private Long id;
    @Schema(description = "Quantity of the product in contents")
    @Min(1)
    private Long quantity;
    @Schema(description = "Service of the current content data transfer object")
    private ServiceDto service;
}
