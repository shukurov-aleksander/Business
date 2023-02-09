package com.ku.business.service;

import com.ku.business.entity.Company;
import com.ku.business.repository.spring.jdbc.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService implements com.ku.business.service.Service<Company> {

    @Autowired
   CompanyRepository<Company> companyRepository;

    @Transactional
    @Override
    public Optional<Company> findById(Long id) {
       return companyRepository.findById(id);
    }

    @Transactional
    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Transactional
    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Transactional
    @Override
    public void update(Company company) {
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
}
