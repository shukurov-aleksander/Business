package com.ku.business.dtomapper;

import com.ku.business.dto.OrderDto;
import com.ku.business.dto.OrderListDto;
import com.ku.business.dto.OrderSaveOrUpdateDto;
import com.ku.business.entity.Order;

import java.util.HashSet;
import java.util.Set;

public class OrderDtoMapper {
    public static OrderDto toDto(Order order) {
        return new OrderDto()
                .setId(order.getId())
                .setOrderStatus(order.getOrderStatus())
                .setCreatedAtUtc(order.getCreatedAtUtc())
                .setCompletedAtUtc(order.getCompletedAtUtc())
                .setContents(new ContentDtoMapper().toListDto(order.getContents())
        );
    }

    public static OrderListDto toListDto(Order order) {
        return new OrderListDto()
                .setId(order.getId())
                .setOrderStatus(order.getOrderStatus()
        );
    }

    public static Set<OrderListDto> toListDto(Set<Order> orders) {
        Set<OrderListDto> orderListDtos = new HashSet<>();
        for (Order order : orders) {
            orderListDtos.add(toListDto(order));
        }
        return orderListDtos;
    }

    public static OrderSaveOrUpdateDto toSaveOrUpdateDto(Order order) {
        return new OrderSaveOrUpdateDto()
                .setId(order.getId())
                .setOrderStatus(order.getOrderStatus())
                .setCreatedAtUtc(order.getCreatedAtUtc())
                .setCompletedAtUtc(order.getCompletedAtUtc()
        );
    }

    public static Order fromSaveOrUpdateDto(OrderSaveOrUpdateDto saveOrUpdateDto) {
        return new Order()
                .setId(saveOrUpdateDto.getId())
                .setOrderStatus(saveOrUpdateDto.getOrderStatus())
                .setCreatedAtUtc(saveOrUpdateDto.getCreatedAtUtc())
                .setCompletedAtUtc(saveOrUpdateDto.getCompletedAtUtc()
        );
    }
}
