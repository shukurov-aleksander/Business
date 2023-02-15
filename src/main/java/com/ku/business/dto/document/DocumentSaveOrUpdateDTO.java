package com.ku.business.dto.document;

import com.ku.business.dto.order.OrderDTO;

public class DocumentSaveOrUpdateDTO {
    Long id;
    OrderDTO order;
    String documentContent;
}
