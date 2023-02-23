package com.ku.business.controller;

import com.ku.business.dto.ServiceDto;
import com.ku.business.dto.ServiceListDto;
import com.ku.business.dto.ServiceSaveDto;
import com.ku.business.service.ServiceService;
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
@Tag(name = "Services", description = "Table of services")
@RequestMapping("/services")
public class ServiceController {
    private ServiceService service;
    @Autowired
    public void setService(ServiceService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    @Operation(summary = "Information about service by ID")
    public Optional<ServiceDto> findById(
            @Parameter(description = "Uniq identification of the service")
            @PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    @Operation(summary = "Get all services from table")
    public List<ServiceListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Save service to database")
    public void save(ServiceSaveDto service) {
        this.service.save(service);
    }

    @PutMapping
    @Operation(summary = "Update existing service in database")
    public void update(ServiceSaveDto service) {
        this.service.update(service);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete service from table by id")
    public void delete(
            @Parameter(description = "Uniq identification of the service")
            @PathVariable Long id) {
        service.delete(id);
    }
}
