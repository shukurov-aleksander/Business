package com.ku.business.dto;

import com.ku.business.entity.OperationType;
import com.ku.business.entity.Order;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DetailSaveOrUpdateDto {
    private Long id;
    private Order order;
    private OperationType operationType;
}
