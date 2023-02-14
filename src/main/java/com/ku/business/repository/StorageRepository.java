package com.ku.business.repository;

import com.ku.business.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<Storage, Long> {
    @Override
    @Query("FROM Storage s LEFT JOIN FETCH s.company LEFT JOIN FETCH s.service WHERE s.id = :id")
    Optional<Storage> findById(Long id);
}
