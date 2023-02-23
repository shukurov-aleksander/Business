package com.ku.business.controller;

import com.ku.business.dto.OrderDto;
import com.ku.business.dto.OrderListDto;
import com.ku.business.dto.OrderSaveDto;
import com.ku.business.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Orders", description = "Table of orders")
@RequestMapping("/orders")
public class OrderController {
    private OrderService service;
    @Autowired
    public void setService(OrderService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    @Operation(summary = "Information about order by ID")
    public Optional<OrderDto> findById(
            @Parameter(description = "Uniq identification of the order")
            @PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    @Operation(summary = "Get all orders from table")
    public List<OrderListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Save order to database")
    public void save(OrderSaveDto order) {
        service.save(order);
    }

    @PutMapping
    @Operation(summary = "Update existing order in database")
    public void update(OrderSaveDto order) {
        service.update(order);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete order from table by id")
    public void delete(
            @Parameter(description = "Uniq identification of the order")
            @PathVariable Long id) {
        service.delete(id);
    }
}
