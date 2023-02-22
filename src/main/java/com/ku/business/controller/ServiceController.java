package com.ku.business.controller;

import com.ku.business.dto.ServiceDto;
import com.ku.business.dto.ServiceListDto;
import com.ku.business.dto.ServiceSaveDto;
import com.ku.business.service.impl.ServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/services")
public class ServiceController {
    @Autowired
    private ServiceServiceImpl service;

    @GetMapping("{id}")
    public Optional<ServiceDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Set<ServiceListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(ServiceSaveDto service) {
        this.service.save(service);
    }

    @PutMapping
    public void update(ServiceSaveDto service) {
        this.service.update(service);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
