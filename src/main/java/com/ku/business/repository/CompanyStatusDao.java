package com.ku.business.repository;

import com.ku.business.entity.CompanyStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyStatusDao {

    public static final String FIND_BY_ID_QUERY = """
            SELECT (id) 
            FROM company_statuses 
            WHERE company_status = :companyStatus               
            """;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Long findIdByName(CompanyStatus companyStatus) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource().addValue("companyStatus", companyStatus.toString());
        return namedParameterJdbcTemplate.query(FIND_BY_ID_QUERY, mapSqlParameterSource, rs -> {
            return rs.getLong("id");
        });
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
