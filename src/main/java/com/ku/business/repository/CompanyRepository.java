package com.ku.business.repository;

import com.ku.business.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Override
    @Query("FROM Company c LEFT JOIN FETCH c.storages LEFT JOIN FETCH c.details WHERE c.id = ?1")
    Optional<Company> findById(Long id);
}
