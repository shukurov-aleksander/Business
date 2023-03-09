package com.ku.business.dto;

import com.ku.business.entity.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Order list data transfer object for list of the companies")
@Accessors(chain = true)
public class OrderListDto {
    @Schema(description = "Id", required = true, example = "1")
    private Long id;
    @Schema(description = "Order status", required = true, example = "CREATED")
    private OrderStatus orderStatus;
}
