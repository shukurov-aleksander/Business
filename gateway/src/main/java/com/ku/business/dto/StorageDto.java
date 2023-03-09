package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Storage data transfer object")
@Accessors(chain = true)
public class StorageDto {
    @Schema(description = "Id", required = true, example = "1")
    private Long id;
    @Schema(description = "Storage quantity", required = true, example = "1")
    private Integer quantity;
    @Schema(description = "Storage company", required = true)
    private CompanyDto company;
    @Schema(description = "Storage service", required = true)
    private ServiceDto service;
}
