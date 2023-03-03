package com.ku.business.repository;

import com.ku.business.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public class CompanyStatusDao {
    public static final String INSERT_QUERY = """
            INSERT INTO company_status_histories (company_id, company_status_id, active, inserted_at_utc, updated_at_utc)
                            VALUES (:company_id, :company_status_id, :active, :inserted_at_utc, updated_at_utc)                
            """;

    public static final String UPDATE_QUERY = """
            UPDATE company_status_histories 
            SET active = :active
            WHERE company_id = :company_id AND avtive = true;               
            """;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(Company company) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("company_id", company.getCompanyName())
                .addValue("company_status_id", company.getCompanyStatus())
                .addValue("active", true)
                .addValue("inserted_at_utc", LocalDateTime.now())
                .addValue("updated_at_utc", LocalDateTime.now());
        namedParameterJdbcTemplate.update(INSERT_QUERY, mapSqlParameterSource);
    }

    @Transactional
    public void update(Company company) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("company_id", company.getCompanyName())
                .addValue("company_status_id", company.getCompanyStatus())
                .addValue("active", false)
                .addValue("updated_at_utc", LocalDateTime.now());
        namedParameterJdbcTemplate.update(UPDATE_QUERY, mapSqlParameterSource);
        save(company);
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
