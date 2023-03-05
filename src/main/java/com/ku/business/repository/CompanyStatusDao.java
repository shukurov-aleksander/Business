package com.ku.business.repository;

import com.ku.business.entity.CompanyStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyStatusDao {
    public static final String SAVE_QUERY = """
        INSERT INTO company_status_histories (company_id, company_status_id, active, inserted_at_utc)
            VALUES (:companyId,
                    (SELECT id FROM company_statuses WHERE company_status = :companyStatus::company_status_enum),
                    TRUE,
                    NOW() AT TIME ZONE 'UTC')          
    """;

    public static final String UPDATE_ACTIVE_TO_FALSE = """
        UPDATE company_status_histories 
        SET active = false, updated_at_utc = NOW() AT TIME ZONE 'UTC'
        WHERE company_id = :companyId AND active = true;               
    """;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void updateStatus(Long companyId, CompanyStatus companyStatus) {
        var mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("companyId", companyId)
                .addValue("companyStatus", companyStatus.toString());
        namedParameterJdbcTemplate.update(SAVE_QUERY, mapSqlParameterSource);
    }

    public void updateActiveToFalse(Long companyId) {
        var mapSqlParameterSource = new MapSqlParameterSource().addValue("companyId", companyId);
        namedParameterJdbcTemplate.update(UPDATE_ACTIVE_TO_FALSE, mapSqlParameterSource);
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}