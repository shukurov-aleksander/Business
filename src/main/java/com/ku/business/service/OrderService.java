package com.ku.business.service;

import com.ku.business.dto.OrderDto;
import com.ku.business.dto.OrderListDto;
import com.ku.business.dto.OrderSaveDto;
import com.ku.business.dtomapper.OrderDtoMapper;
import com.ku.business.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Optional<OrderDto> findById(Long id) {
        return Optional.of(OrderDtoMapper.toDto(repository.findById(id).get()));
    }

    public List<OrderListDto> findAll() {
        return OrderDtoMapper.toListDto(repository.findAll());
    }

    public void save(OrderSaveDto order) {
        repository.save(OrderDtoMapper.fromSaveDto(order));
    }

    public void update(OrderSaveDto order) {
        repository.save(OrderDtoMapper.fromSaveDto(order));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
