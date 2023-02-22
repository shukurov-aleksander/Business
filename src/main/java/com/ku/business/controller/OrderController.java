package com.ku.business.controller;

import com.ku.business.entity.Order;
import com.ku.business.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    @Autowired
    private OrderServiceImpl service;

    @GetMapping("{id}")
    public Optional<Order> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Order> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(Order order) {
        service.save(order);
    }

    @PutMapping
    public void update(Order order) {
        service.update(order);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
