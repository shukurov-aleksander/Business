package com.ku.business.repository.spring;

import com.ku.business.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository {
    void save(Company company);
    void update(Company company);
    void deleteById(Long id);
    List<Company> findAll();
    Optional<Company> findById(Long id);
}
