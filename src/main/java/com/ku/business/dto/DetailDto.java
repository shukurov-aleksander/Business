package com.ku.business.dto;

import com.ku.business.entity.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Detail data transfer object")
@Accessors(chain = true)
public class DetailDto {
    @Schema(description = "Uniq identification of the detail data transfer object")
    private Long id;
    @Schema(description = "Company of the detail data transfer object")
    private CompanyDto company;
    @Schema(description = "Order of the detail data transfer object")
    private OrderDto order;
    @Schema(description = "Type of operation")
    private OperationType operationType;
}
