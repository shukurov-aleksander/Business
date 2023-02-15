package com.ku.business.dto.order;

import com.ku.business.dto.content.ContentListDTO;
import com.ku.business.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    Long id;
    OrderStatus orderStatus;
    LocalDateTime createdAtUtc;
    LocalDateTime completedAtUtc;
    List<ContentListDTO> contents;
}
