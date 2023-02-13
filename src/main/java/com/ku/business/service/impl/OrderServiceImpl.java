package com.ku.business.service.impl;

import com.ku.business.entity.Order;
import com.ku.business.repository.OrderRepository;
import com.ku.business.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements CrudService<Order> {
    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Order order) {
        repository.save(order);
    }

    @Override
    public void update(Order order) {
        repository.save(order);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
