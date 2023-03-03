package com.ku.business.repository;

import com.ku.business.entity.Company;
import com.ku.business.entity.CompanyStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDateTime;

public class CompanyStatusHistoryDao {

    public static final String INSERT_QUERY = """
            INSERT INTO company_status_histories (company_id, company_status_id, active, inserted_at_utc)
                            VALUES (:companyId, :companyStatusId, :active, :insertedAtUtc)                
            """;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(Company company) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("companyId", new CompanyDao().findIdByName(company))
                .addValue("companyStatusId", new CompanyStatusDao().findIdByName(company.getCompanyStatus()))
                .addValue("active", false)
                .addValue("insertedAtUtc", LocalDateTime.now());
        namedParameterJdbcTemplate.update(INSERT_QUERY, mapSqlParameterSource);
    }

    public void update(CompanyStatus companyStatus) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("companyName", company.getCompanyName())
                .addValue("taxNumber", company.getTaxNumber())
                .addValue("userId", company.getUserId())
                .addValue("isGovernmentAgency", company.getIsGovernmentAgency())
                .addValue("active", company.getCompanyStatus().equals(CompanyStatus.ACTIVE))
                .addValue("inserterAtUtc", company.getCompanyStatus().equals(CompanyStatus.ACTIVE) ? )

        namedParameterJdbcTemplate.update(INSERT_QUERY, mapSqlParameterSource);
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
