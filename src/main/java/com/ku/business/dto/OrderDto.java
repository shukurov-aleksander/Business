package com.ku.business.dto;

import com.ku.business.entity.OrderStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Accessors(chain = true)
public class OrderDto {
    private Long id;
    private OrderStatus orderStatus;
    private LocalDateTime createdAtUtc;
    private LocalDateTime completedAtUtc;
    private Set<ContentListDto> contents;
}
