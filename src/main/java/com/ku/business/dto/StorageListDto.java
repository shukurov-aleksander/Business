package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Storage list data transfer object for list of the companies")
@Accessors(chain = true)
public class StorageListDto {
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Storage quantity", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer quantity;
}