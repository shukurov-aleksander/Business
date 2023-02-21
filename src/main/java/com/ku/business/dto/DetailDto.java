package com.ku.business.dto;

import com.ku.business.entity.OperationType;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DetailDto {
    private Long id;
    private CompanyDto company;
    private OrderDto order;
    private OperationType operationType;
}
