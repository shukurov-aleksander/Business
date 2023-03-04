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
                            VALUES (
                                (SELECT COUNT(*) FROM companies),
                                (SELECT id FROM company_statuses WHERE company_status = :companyStatus::company_status_enum),
                                :active, 
                                :insertedAtUtc, :updatedAtUtc)                
            """;

    public static final String SAVE_QUERY = """
            INSERT INTO company_status_histories (company_id, company_status_id, active, inserted_at_utc, updated_at_utc)
                            VALUES (
                            :companyId,
                            (SELECT id FROM company_statuses WHERE company_status = :companyStatus::company_status_enum),
                            TRUE,
             	            (SELECT inserted_at_utc
            	            FROM company_status_histories
            	            WHERE (company_id = :companyId AND company_status_id = 
            	                (SELECT company_status_id FROM company_status_histories WHERE company_id = :companyId 
            	                AND id = (SELECT max(id) FROM company_status_histories)) 
            	                AND id = (SELECT max(id) FROM company_status_histories))),
                            :updatedAtUtc)                            
            """;

    public static final String UPDATE_QUERY = """
            UPDATE company_status_histories 
            SET active = :active
            WHERE company_id = :companyId AND active = true;               
            """;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(Company company) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("companyStatus", company.getCompanyStatus().toString())
                .addValue("active", true)
                .addValue("insertedAtUtc", LocalDateTime.now())
                .addValue("updatedAtUtc", LocalDateTime.now());
        namedParameterJdbcTemplate.update(INSERT_QUERY, mapSqlParameterSource);
    }

    @Transactional
    public void update(Company company) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("companyId", company.getId())
                .addValue("active", false)
                .addValue("updatedAtUtc", LocalDateTime.now())
                .addValue("companyStatus", company.getCompanyStatus().toString());
        namedParameterJdbcTemplate.update(UPDATE_QUERY, mapSqlParameterSource);
       namedParameterJdbcTemplate.update(SAVE_QUERY, mapSqlParameterSource);
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
