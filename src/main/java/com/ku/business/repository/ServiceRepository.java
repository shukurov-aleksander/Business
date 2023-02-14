package com.ku.business.repository;

import com.ku.business.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Override
    @Query("FROM Service s WHERE s.id = :id")
    Optional<Service> findById(Long id);
}
