package com.ku.business.repository;

import com.ku.business.entity.CompanyStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyStatusDao {
    public static final String SAVE_QUERY = """
        INSERT INTO company_status_histories as csh (company_id, company_status_id, active, inserted_at_utc)
            VALUES (:companyId,
                    (SELECT id FROM company_statuses WHERE company_status = :companyStatus::company_status_enum),
                    TRUE,
                    NOW() AT TIME ZONE 'UTC')
        ON CONFLICT (company_id) WHERE active = TRUE
        DO UPDATE 
            SET active = FALSE , updated_at_utc = NOW() AT TIME ZONE 'UTC' 
            WHERE csh.company_status_id != (SELECT id FROM company_statuses WHERE company_status = :companyStatus::company_status_enum)
    """;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int updateStatus(Long companyId, CompanyStatus companyStatus) {
        var mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("companyId", companyId)
                .addValue("companyStatus", companyStatus.toString());
      return namedParameterJdbcTemplate.update(SAVE_QUERY, mapSqlParameterSource);
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}