package com.ku.business.service;

import com.ku.business.entity.Order;
import com.ku.business.repository.hibernate.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements CrudService<Order> {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order findById(Long id) {
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
        repository.update(order);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
