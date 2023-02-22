package com.ku.business.controller;

import com.ku.business.dto.OrderDto;
import com.ku.business.dto.OrderListDto;
import com.ku.business.dto.OrderSaveDto;
import com.ku.business.service.OrderService;
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
@RequestMapping("/orders")
public class OrderController {
    private OrderService service;
    @Autowired
    public void setService(OrderService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Optional<OrderDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<OrderListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(OrderSaveDto order) {
        service.save(order);
    }

    @PutMapping
    public void update(OrderSaveDto order) {
        service.update(order);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
