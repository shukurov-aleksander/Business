package com.ku.business.dto;

import com.ku.business.entity.OrderStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class OrderSaveOrUpdateDto {
    private Long id;
    private OrderStatus orderStatus;
    private LocalDateTime createdAtUtc;
    private LocalDateTime completedAtUtc;
}
