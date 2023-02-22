package com.ku.business.service.impl;

import com.ku.business.dto.OrderDto;
import com.ku.business.dto.OrderListDto;
import com.ku.business.dto.OrderSaveDto;
import com.ku.business.dtomapper.OrderDtoMapper;
import com.ku.business.repository.OrderRepository;
import com.ku.business.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.Optional;

@Service
public class OrderServiceImpl implements CrudService<OrderDto, OrderListDto, OrderSaveDto> {
    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<OrderDto> findById(Long id) {
        return Optional.of(OrderDtoMapper.toDto(repository.findById(id).get()));
    }

    @Override
    public Set<OrderListDto> findAll() {
        return OrderDtoMapper.toListDto(repository.findAll());
    }

    @Override
    public void save(OrderSaveDto order) {
        repository.save(OrderDtoMapper.fromSaveDto(order));
    }

    @Override
    public void update(OrderSaveDto order) {
        repository.save(OrderDtoMapper.fromSaveDto(order));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
