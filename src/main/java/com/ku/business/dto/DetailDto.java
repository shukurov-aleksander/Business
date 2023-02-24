package com.ku.business.dto;

import com.ku.business.entity.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Detail data transfer object")
@Accessors(chain = true)
public class DetailDto {
    @Schema(description = "Id", required = true, example = "1")
    private Long id;
    @Schema(description = "Detail company", required = true)
    private CompanyDto company;
    @Schema(description = "Detail order", required = true)
    private OrderDto order;
    @Schema(description = "Detail operation type", required = true, example = "PURCHASE")
    private OperationType operationType;
}
