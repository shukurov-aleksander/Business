package com.ku.business.dto;

import com.ku.business.entity.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Schema(description = "Order data transfer object")
@Accessors(chain = true)
public class OrderDto {
    @Schema(description = "Uniq identification of the order data transfer object")
    private Long id;
    @Schema(description = "Status of the order")
    private OrderStatus orderStatus;
    @Schema(description = "What time order was created")
    private LocalDateTime createdAtUtc;
    @Schema(description = "What time order was completed")
    private LocalDateTime completedAtUtc;
    @Schema(description = "List of the contents of the order data transfer object")
    private Set<ContentListDto> contents;
}
