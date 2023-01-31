package com.ku.business.service;

import com.ku.business.entity.Company;
import com.ku.business.repository.spring.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CompanyServiceImpl implements CompanyService{
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void update(Company company) {
        companyRepository.update(company);
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }
}
