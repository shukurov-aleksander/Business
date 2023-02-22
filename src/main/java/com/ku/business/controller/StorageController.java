package com.ku.business.controller;

import com.ku.business.dto.StorageDto;
import com.ku.business.dto.StorageListDto;
import com.ku.business.dto.StorageSaveDto;
import com.ku.business.service.StorageService;
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
@RequestMapping("/storages")
public class StorageController {
    private StorageService service;
    @Autowired
    public void setService(StorageService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Optional<StorageDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<StorageListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(StorageSaveDto storage) {
        service.save(storage);
    }

    @PutMapping
    public void update(StorageSaveDto storage) {
        service.update(storage);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
