package com.ku.business.dto.detail;

import com.ku.business.entity.OperationType;
import com.ku.business.entity.Order;

public class DetailSaveOrUpdateDTO {
    Long id;
    Order order;
    OperationType operationType;
}
