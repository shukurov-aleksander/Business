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
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Order status")
    private OrderStatus orderStatus;
    @Schema(description = "Order created at")
    private LocalDateTime createdAtUtc;
    @Schema(description = "Order completed at")
    private LocalDateTime completedAtUtc;
    @Schema(description = "Order contents", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<ContentListDto> contents;
}
