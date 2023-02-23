package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;

@Data
@Schema(description = "Company list data transfer object for list of the companies")
@Accessors(chain = true)
public class StorageListDto {
    @Schema(description = "Uniq identification of the storage data transfer object")
    private Long id;
    @Schema(description = "Quantity of the products on the storage")
    @Min(1)
    private Integer quantity;
}
