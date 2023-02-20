package com.ku.business.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DocumentDto {
    private Long id;
    private OrderDto order;
    private String documentContent;
}
