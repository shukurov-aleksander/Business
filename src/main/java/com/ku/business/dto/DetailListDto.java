package com.ku.business.dto;

import com.ku.business.entity.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Detail list data transfer object for list of the companies")
@Accessors(chain = true)
public class DetailListDto {
    @Schema(description = "Uniq identification of the detail data transfer object")
    private Long id;
    @Schema(description = "Order of the detail data transfer object")
    private OrderDto order;
    @Schema(description = "Type of operation")
    private OperationType operationType;
}
