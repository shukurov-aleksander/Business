package com.ku.business.controller;

import com.ku.business.dto.CompanyDto;
import com.ku.business.dto.CompanyListDto;
import com.ku.business.dto.CompanySaveDto;
import com.ku.business.service.CompanyService;
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
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService service;

    @Autowired
    public void setService(CompanyService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Optional<CompanyDto> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping
    public List<CompanyListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(CompanySaveDto company) {
        service.save(company);
    }

    @PutMapping
    public void update(CompanySaveDto company) {
        service.update(company);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
