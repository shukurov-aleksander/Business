package com.ku.business.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<K extends Serializable, E> {
    E findById(K k);
    List<E> findAll();
    void save(E e);
    void update(E e);
    void delete(K k);
}
