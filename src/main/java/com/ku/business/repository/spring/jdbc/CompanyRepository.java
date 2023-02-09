package com.ku.business.repository.spring.jdbc;

import com.ku.business.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository<P> extends JpaRepository<Company, Long> {
    List<Company> findAllByCompanyName(String companyName);
}
