package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Storage data transfer object")
@Accessors(chain = true)
public class StorageDto {
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Storage quantity", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer quantity;
    @Schema(description = "Storage company", requiredMode = Schema.RequiredMode.REQUIRED)
    private CompanyDto company;
    @Schema(description = "Storage service", requiredMode = Schema.RequiredMode.REQUIRED)
    private ServiceDto service;
}