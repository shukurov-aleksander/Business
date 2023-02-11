package com.ku.business.service;

import com.ku.business.entity.Order;
import com.ku.business.repository.spring.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements CrudService<Order> {
    private final OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
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
    public void update(Order order, Long id) {
        repository.update(order,id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
