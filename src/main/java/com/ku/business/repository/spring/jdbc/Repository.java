package com.ku.business.repository.spring.jdbc;

import com.ku.business.entity.Company;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface Repository<T> {
    Optional<T> findById(Long id);
    Optional<T> findCompany(Long id);
    List<T> findAll();
    void save(T obj);
    void update(T obj);

    void deleteById(Long id);
}
