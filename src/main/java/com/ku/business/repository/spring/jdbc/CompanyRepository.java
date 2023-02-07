package com.ku.business.repository.spring.jdbc;

import com.ku.business.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository {
    Optional<Company> findById(Long id);
    Optional<Company> findCompany(Long id);
    List<Company> findAll();
    void save(Company company);
    void update(Company company);
    void deleteById(Long id);
}
