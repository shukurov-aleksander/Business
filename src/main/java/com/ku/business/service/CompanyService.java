package com.ku.business.service;

import com.ku.business.entity.Company;
import com.ku.business.repository.hibernate.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService implements CrudService<Company> {
    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Company findById(Long id) {
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
    public void update(Company company) {
        repository.update(company);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
