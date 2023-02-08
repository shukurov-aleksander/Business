package com.ku.business.service;

import com.ku.business.entity.Company;
import com.ku.business.repository.spring.jdbc.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component

public class CompanyServiceImpl implements Service {
    @Autowired
    Repository<Company> companyRepository;

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }
    @Override
    public Optional<Company> findCompany(Long id) {
        return companyRepository.findCompany(id);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public void save(Object obj) {
        companyRepository.save((Company) obj);
    }
    @Override
    public void update(Object obj) {
        companyRepository.update((Company) obj);
    }


    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
}
