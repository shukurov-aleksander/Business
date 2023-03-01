package com.ku.business.service;

import com.ku.business.dto.OrderDto;
import com.ku.business.dto.OrderListDto;
import com.ku.business.dto.OrderSaveDto;
import com.ku.business.dtomapper.OrderDtoMapper;
import com.ku.business.repository.OrderDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Optional<OrderDto> findById(Long id) {
        return Optional.of(OrderDtoMapper.toDto(orderDao.findById(id).get()));
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
}
