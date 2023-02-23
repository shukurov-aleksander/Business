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
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Order status")
    private OrderStatus orderStatus;
    @Schema(description = "Order created at")
    private LocalDateTime createdAtUtc;
    @Schema(description = "Order completed at")
    private LocalDateTime completedAtUtc;
}
