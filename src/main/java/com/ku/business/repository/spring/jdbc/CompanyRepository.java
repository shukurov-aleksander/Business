package com.ku.business.repository.spring.jdbc;

import com.ku.business.entity.Company;
import com.ku.business.exception.RepositoryException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component

public class CompanyRepository implements Repository<Company>{
    public JdbcTemplate jdbcTemplate;

    public CompanyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Company> findById(Long id) {
        String sql = "SELECT id, company_name, tax_number, is_government_agency, user_id FROM companies where id = ?";
        Company company = null;
        try {
            company = jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<>(Company.class));
        } catch (RepositoryException ex) {
            throw ex;
        }
        return Optional.ofNullable(company);
    }

    @Override
    public Optional<Company> findCompany(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Company> findAll() {
        String sql = "SELECT id, company_name, tax_number, is_government_agency, user_id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Company.class));
    }

    @Override
    public void save(Company obj) {
        String sql = "INSERT INTO companies(company_name, tax_number, is_government_agency, user_id) VALUES (?, ?, ?, ?)";
        int insert = jdbcTemplate.update(sql, company.getCompanyName(), company.getTaxNumber(), company.getIsGovernmentAgency(), company.getUserId());
        if (insert == 1) {
            System.out.println("Company successfully saved");
        }
    }

    @Override
    public void update(Company obj) {
        String sql = "UPDATE companies SET company_name = ?, tax_number = ?, is_government_agency = ?, user_id = ?";
        int update = jdbcTemplate.update(sql, company.getCompanyName(), company.getTaxNumber(), company.getIsGovernmentAgency(), company.getUserId());
        if (update == 1) {
            System.out.println("Company successfully updated");
        }
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM companies WHERE id = ?");
    }
}
