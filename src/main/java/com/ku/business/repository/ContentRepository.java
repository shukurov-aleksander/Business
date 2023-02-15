package com.ku.business.repository;

import com.ku.business.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Long> {
    @Override
    @Query("FROM Content c LEFT JOIN FETCH c.orders LEFT JOIN FETCH c.service WHERE c.id = :id")
    Optional<Content> findById(Long id);
}
