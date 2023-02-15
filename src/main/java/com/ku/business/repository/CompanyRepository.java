package com.ku.business.repository;

import com.ku.business.dto.company.CompanyDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyDTO, Long> {
    @Override
    @Query("FROM Company c LEFT JOIN FETCH c.storages LEFT JOIN FETCH c.details WHERE c.id = :id")
    Optional<CompanyDTO> findById(Long id);
}
