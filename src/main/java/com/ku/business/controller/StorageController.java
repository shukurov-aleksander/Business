package com.ku.business.controller;

import com.ku.business.dto.StorageDto;
import com.ku.business.dto.StorageListDto;
import com.ku.business.dto.StorageSaveDto;
import com.ku.business.service.impl.StorageServiceImpl;
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
@RequestMapping(value = "/storages")
public class StorageController {
    @Autowired
    private StorageServiceImpl service;

    @GetMapping("{id}")
    public Optional<StorageDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Set<StorageListDto> findAll() {
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
