package com.ku.business.dto;

import com.ku.business.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    Long id;
    OrderStatus orderStatus;
    LocalDateTime createdAtUtc;
    LocalDateTime completedAtUtc;
    List<ContentListDto> contents;

    public OrderDto(
            Long id,
            OrderStatus orderStatus,
            LocalDateTime createdAtUtc,
            LocalDateTime completedAtUtc,
            List<ContentListDto> contents
    ) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.createdAtUtc = createdAtUtc;
        this.completedAtUtc = completedAtUtc;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreatedAtUtc() {
        return createdAtUtc;
    }

    public void setCreatedAtUtc(LocalDateTime createdAtUtc) {
        this.createdAtUtc = createdAtUtc;
    }

    public LocalDateTime getCompletedAtUtc() {
        return completedAtUtc;
    }

    public void setCompletedAtUtc(LocalDateTime completedAtUtc) {
        this.completedAtUtc = completedAtUtc;
    }

    public List<ContentListDto> getContents() {
        return contents;
    }

    public void setContents(List<ContentListDto> contents) {
        this.contents = contents;
    }
}
