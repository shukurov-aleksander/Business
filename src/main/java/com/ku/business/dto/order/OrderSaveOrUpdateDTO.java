package com.ku.business.dto.order;

import com.ku.business.entity.OrderStatus;

import java.time.LocalDateTime;

public class OrderSaveOrUpdateDTO {
    Long id;
    OrderStatus orderStatus;
    LocalDateTime createdAtUtc;
    LocalDateTime completedAtUtc;
}
