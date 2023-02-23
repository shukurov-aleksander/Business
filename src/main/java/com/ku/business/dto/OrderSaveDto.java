package com.ku.business.dto;

import com.ku.business.entity.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Schema(description = "Order save or update data transfer object")
@Accessors(chain = true)
public class OrderSaveDto {
    @Schema(description = "Uniq identification of the order data transfer object")
    private Long id;
    @Schema(description = "Status of the order")
    private OrderStatus orderStatus;
    @Schema(description = "What time order was created")
    private LocalDateTime createdAtUtc;
    @Schema(description = "What time order was completed")
    private LocalDateTime completedAtUtc;
}
