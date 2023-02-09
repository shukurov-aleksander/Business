package com.ku.business.controller;

import com.ku.business.entity.Company;
import com.ku.business.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/{id}")
    public Optional<Company> findById(@PathVariable Long id) {
        return companyService.findById(id);
    }
    @GetMapping
    public List<Company> findAll() {
        return companyService.findAll();
    }
    @PostMapping
    public void save(@RequestBody Company company) {
       companyService.save(company);
    }
    @PutMapping
    public void update(@RequestBody Company company) {
        companyService.update(company);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        companyService.deleteById(id);
    }
}
