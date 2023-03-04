package com.ku.business.repository;

import com.ku.business.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderDao extends JpaRepository<Order, Long> {
    @Override
    @Query("FROM Order o LEFT JOIN FETCH o.contents WHERE o.id = :id")
    Optional<Order> findById(Long id);
}