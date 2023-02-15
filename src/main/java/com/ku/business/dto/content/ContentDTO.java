package com.ku.business.dto.content;

import com.ku.business.dto.order.OrderListDTO;
import com.ku.business.dto.service.ServiceDTO;

import java.util.List;

public class ContentDTO {
    Long id;
    Long quantity;
    ServiceDTO service;
    List<OrderListDTO> orders;
}
