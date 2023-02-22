package com.ku.business.service;

import java.util.Optional;
import java.util.Set;

public interface CrudService<T, L, M> {
    Optional<T> findById(Long id);
    Set<L> findAll();
    void save(M m);
    void update(M m);
    void delete(Long id);
}
