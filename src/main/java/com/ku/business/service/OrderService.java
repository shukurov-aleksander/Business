package com.ku.business.service;

import com.ku.business.dto.OrderDto;
import com.ku.business.dto.OrderListDto;
import com.ku.business.dto.OrderSaveDto;
import com.ku.business.dtomapper.OrderDtoMapper;
import com.ku.business.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ku.business.exception.ServiceException.notFoundException;

@Service
public class OrderService {
    private OrderDao orderDao;

    public Optional<OrderDto> findById(Long id) {
        try {
            return Optional.of(OrderDtoMapper.toDto(orderDao.findById(id).get()));
        } catch (RuntimeException runtimeException) {
            throw notFoundException(String.format("Can't find order with id=%d!", id), runtimeException);
        }
    }

    public List<OrderListDto> findAll() {
        return OrderDtoMapper.toListDto(orderDao.findAll());
    }

    public void save(OrderSaveDto order) {
        orderDao.save(OrderDtoMapper.fromSaveDto(order));
    }

    public void update(OrderSaveDto order) {
        orderDao.save(OrderDtoMapper.fromSaveDto(order));
    }

    public void delete(Long id) {
        orderDao.deleteById(id);
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}