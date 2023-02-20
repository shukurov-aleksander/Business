package com.ku.business.dto;

import com.ku.business.entity.OrderStatus;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderListDto {
    private Long id;
    private OrderStatus orderStatus;
}
