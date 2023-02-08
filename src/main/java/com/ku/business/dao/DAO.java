package com.ku.business.dao;

import com.ku.business.entity.Company;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<Company> findById(Long id);
    Optional<Company> findCompany(Long id);
    List<Company> findAll();
    void save(Company company);
    void update(Company company);
    void deleteById(Long id);
}
