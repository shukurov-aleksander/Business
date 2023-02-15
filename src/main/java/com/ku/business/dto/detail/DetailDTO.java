package com.ku.business.dto.detail;

import com.ku.business.dto.company.CompanyDTO;
import com.ku.business.dto.order.OrderDTO;
import com.ku.business.entity.OperationType;

public class DetailDTO {
    Long id;
    CompanyDTO company;
    OrderDTO order;
    OperationType operationType;
}
