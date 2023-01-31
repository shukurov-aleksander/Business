package com.ku.business.service;

import com.ku.business.entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CompanyService {
    void save(Company company);
    void update(Company company);
    void deleteById(Long id);
    List<Company> findAll();
    Optional<Company> findById(Long id);
}
