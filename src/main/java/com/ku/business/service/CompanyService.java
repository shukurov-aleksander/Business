package com.ku.business.service;

import com.ku.business.entity.Company;
import com.ku.business.repository.spring.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService implements CrudService<Company> {
    private final CompanyRepository repository;
    @Autowired
    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Company> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Company> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Company company) {
        repository.save(company);
    }

    @Override
    public void update(Company company, Long id) {
        repository.update(company, id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
