package com.ku.business.service;

import java.util.List;

public interface RepositoryAccess<E> {
    E findByID(Long id);
    List<E> findAll();
    void save();
    void update();
    void delete();
}
