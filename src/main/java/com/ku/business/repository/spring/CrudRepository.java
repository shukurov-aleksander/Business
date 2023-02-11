package com.ku.business.repository.spring;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    Optional<T> findById(Long id);
    List<T> findAll();
    void update(T t, Long id);
    void save(T t);
    void delete(Long id);
}
