package com.ku.business.repository;

import com.ku.business.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DetailDao extends JpaRepository<Detail, Long> {
    @Override
    @Query("FROM Detail d LEFT JOIN FETCH d.company LEFT JOIN FETCH d.order WHERE d.id = :id")
    Optional<Detail> findById(Long id);
}
