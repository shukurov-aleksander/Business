package com.ku.business.service;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public interface CrudService<T> {
    Optional<T> findById(Long id);
    List<T> findAll();
    void save(T t);
    void update(T t, Long id);
    void delete(Long id);
}
