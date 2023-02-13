package com.ku.business.repository;

import com.ku.business.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DetailRepository extends JpaRepository<Detail, Long> {
}
