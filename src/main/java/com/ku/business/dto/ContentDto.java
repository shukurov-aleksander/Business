package com.ku.business.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
public class ContentDto {
    private Long id;
    private Long quantity;
    private ServiceDto service;
    private Set<OrderListDto> orders;
}
