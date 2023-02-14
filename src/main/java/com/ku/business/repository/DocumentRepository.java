package com.ku.business.repository;

import com.ku.business.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Override
    @Query("FROM Document d LEFT JOIN FETCH d.order WHERE d.id = ?1")
    Optional<Document> findById(Long id);
}
