package com.ku.business.dtomapper;

import com.ku.business.dto.OrderDto;
import com.ku.business.dto.OrderListDto;
import com.ku.business.dto.OrderSaveOrUpdateDto;
import com.ku.business.entity.Order;

import java.util.HashSet;
import java.util.Set;

public class OrderDtoMapper {
    public static OrderDto toDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getOrderStatus(),
                order.getCreatedAtUtc(),
                order.getCompletedAtUtc(),
                new ContentDtoMapper().toDtoList(order.getContents())
        );
    }

    public static OrderListDto toListDto(Order order) {
        return new OrderListDto(
                order.getId(),
                order.getOrderStatus()
        );
    }

    public static Set<OrderListDto> toDtoList(Set<Order> orders) {
        Set<OrderListDto> orderListDtos = new HashSet<>();
        for (Order order : orders) {
            orderListDtos.add(toListDto(order));
        }
        return orderListDtos;
    }

    public static OrderSaveOrUpdateDto toSaveOrUpdateDto(Order order) {
        return new OrderSaveOrUpdateDto(
                order.getId(),
                order.getOrderStatus(),
                order.getCreatedAtUtc(),
                order.getCompletedAtUtc()
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
