package com.ku.business.controller;

import com.ku.business.dto.CompanyDto;
import com.ku.business.dto.CompanyListDto;
import com.ku.business.dto.CompanySaveDto;
import com.ku.business.service.CompanyService;
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
@Tag(name = "Companies", description = "Table of companies")
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService service;

    @Autowired
    public void setService(CompanyService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Information about company by ID")
    public Optional<CompanyDto> findById(
            @Parameter(description = "Uniq identification of the company")
            @PathVariable("id") Long id){
        return service.findById(id);
    }

    @GetMapping
    @Operation(summary = "Get all companies from table")
    public List<CompanyListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Save company to database")
    public void save(CompanySaveDto company) {
        service.save(company);
    }

    @PutMapping
    @Operation(summary = "Update existing company in database")
    public void update(CompanySaveDto company) {
        service.update(company);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete company from table by id")
    public void delete(
            @Parameter(description = "Uniq identification of the company")
            @PathVariable Long id) {
        service.delete(id);
    }
}
