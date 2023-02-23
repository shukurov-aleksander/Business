package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;

@Data
@Schema(description = "Company save or update data transfer object")
@Accessors(chain = true)
public class StorageSaveDto {
    @Schema(description = "Uniq identification of the storage data transfer object")
    private Long id;
    @Schema(description = "Quantity of the products on the storage")
    @Min(1)
    private Integer quantity;
}
