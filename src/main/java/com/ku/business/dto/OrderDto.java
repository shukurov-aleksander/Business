package com.ku.business.dto;

import com.ku.business.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    private Long id;
    private OrderStatus orderStatus;
    private LocalDateTime createdAtUtc;
    private LocalDateTime completedAtUtc;
    private List<ContentDto> contents;

}
