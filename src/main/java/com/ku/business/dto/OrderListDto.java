package com.ku.business.dto;

import com.ku.business.entity.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Order list data transfer object for list of the companies")
@Accessors(chain = true)
public class OrderListDto {
    @Schema(description = "Uniq identification of the order data transfer object")
    private Long id;
    @Schema(description = "Status of the order")
    private OrderStatus orderStatus;
}
