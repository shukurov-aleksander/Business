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

@RestController
@Tag(name = "Companies", description = "Companies information")
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService service;

    @GetMapping("/{id}")
    @Operation(summary = "Find company by id")
    public CompanyDto findById(
        @Parameter( description = "Company id", required = true, example = "1")
        @PathVariable("id") Long id
    ){
        return service.findById(id).get();
    }

    @GetMapping
    @Operation(summary = "Find companies")
    public List<CompanyListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Save company")
    public void save(CompanySaveDto company) {
        service.save(company);
    }

    @PutMapping
    @Operation(summary = "Update company")
    public void update(CompanySaveDto company) {
        service.update(company);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete company by id")
    public void delete(
        @Parameter(description = "Company id", required = true, example = "1")
        @PathVariable Long id
    ) {
        service.delete(id);
    }

    @Autowired
    public void setService(CompanyService service) {
        this.service = service;
    }
}
