package com.ku.business.controller;

import com.ku.business.entity.Service;
import com.ku.business.service.impl.ServiceServiceImpl;
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
@RequestMapping(value = "/services")
public class ServiceController {
    @Autowired
    private ServiceServiceImpl service;

    @GetMapping("{id}")
    public Optional<Service> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Service> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(Service service) {
        this.service.save(service);
    }

    @PutMapping
    public void update(Service service) {
        this.service.update(service);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
