package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import java.util.Set;

@Data
@Schema(description = "Content data transfer object")
@Accessors(chain = true)
public class ContentDto {
    @Schema(description = "Uniq identification of the content data transfer object")
    private Long id;
    @Schema(description = "Quantity of the product in contents")
    @Min(1)
    private Long quantity;
    @Schema(description = "Service of the current content data transfer object")
    private ServiceDto service;
    @Schema(description = "List of the orders of the current content data transfer object")
    private Set<OrderListDto> orders;
}
