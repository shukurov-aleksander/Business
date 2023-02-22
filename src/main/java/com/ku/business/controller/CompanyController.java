package com.ku.business.controller;

import com.ku.business.entity.Company;
import com.ku.business.service.impl.CompanyServiceImpl;
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
@RequestMapping(value = "/companies")
public class CompanyController {
    @Autowired
    private CompanyServiceImpl service;

    @GetMapping("/{id}")
    public Optional<Company> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping
    public List<Company> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(Company company) {
        service.save(company);
    }

    @PutMapping
    public void update(Company company) {
        service.update(company);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
