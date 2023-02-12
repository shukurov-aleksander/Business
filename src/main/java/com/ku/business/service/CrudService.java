package com.ku.business.service;

import java.util.List;

@org.springframework.stereotype.Service
public interface CrudService<T> {
    T findById(Long id);
    List<T> findAll();
    void save(T t);
    void update(T t);
    void delete(Long id);
}
