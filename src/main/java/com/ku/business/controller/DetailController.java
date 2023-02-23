package com.ku.business.controller;

import com.ku.business.dto.DetailDto;
import com.ku.business.dto.DetailListDto;
import com.ku.business.dto.DetailSaveDto;
import com.ku.business.service.DetailService;
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
@Tag(name = "Details", description = "Details information")
@RequestMapping("/details")
public class DetailController {
    private DetailService service;
    @Autowired
    public void setService(DetailService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    @Operation(summary = "Find detail by id")
    public Optional<DetailDto> findById(
        @Parameter(description = "Detail id", required = true, example = "1")
        @PathVariable Long id
    ) {
        return service.findById(id);
    }

    @GetMapping
    @Operation(summary = "Find details")
    public List<DetailListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Save detail")
    public void save(DetailSaveDto detail) {
        service.save(detail);
    }

    @PutMapping
    @Operation(summary = "Update detail")
    public void update(DetailSaveDto detail) {
        service.update(detail);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete detail by id")
    public void delete(
        @Parameter(description = "Detail id", required = true, example = "1")
        @PathVariable Long id
    ) {
        service.delete(id);
    }
}
