package com.ku.business.controller;

import com.ku.business.dto.StorageDto;
import com.ku.business.dto.StorageListDto;
import com.ku.business.dto.StorageSaveDto;
import com.ku.business.service.StorageService;
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
@Tag(name = "Storages", description = "Storages information")
@RequestMapping("/storages")
public class StorageController {
    private StorageService service;
    @Autowired
    public void setService(StorageService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    @Operation(summary = "Find storage by id")
    public Optional<StorageDto> findById(
        @Parameter(description = "Storage id", required = true, example = "1")
        @PathVariable Long id
    ) {
        return service.findById(id);
    }

    @GetMapping
    @Operation(summary = "Find storages")
    public List<StorageListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Save storage")
    public void save(StorageSaveDto storage) {
        service.save(storage);
    }

    @PutMapping
    @Operation(summary = "Update storage")
    public void update(StorageSaveDto storage) {
        service.update(storage);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete storage by id")
    public void delete(
        @Parameter(description = "Storage id", required = true, example = "1")
        @PathVariable Long id
    ) {
        service.delete(id);
    }
}
