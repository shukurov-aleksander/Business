package com.ku.business.repository;

import com.ku.business.dto.CompanyListDto;
import com.ku.business.entity.CompanyStatus;
import com.ku.business.filter.CompanyFilter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class CompanyDao {
    private static final String FIND_ALL_QUERY = """
        SELECT c.id, c.company_name, c.tax_number, c.is_government_agency, c.user_id, cs.company_status
        FROM companies c
            LEFT JOIN company_status_histories csh on csh.company_id = c.id AND csh.active = true
            LEFT JOIN company_statuses cs on csh.company_status_id = cs.id
        WHERE (:companyName::text IS NULL OR c.company_name = :companyName)
            AND (:taxNumber::text  IS NULL OR c.tax_number = :taxNumber)
            AND (:userId::integer IS NULL OR c.user_id = :userId)    
            AND (:isGovernmentAgency::text IS NULL OR c.is_government_agency = :isGovernmentAgency)
            AND (:companyStatus::company_status_enum IS NULL OR cs.company_status = :companyStatus::company_status_enum)
        LIMIT :limit OFFSET :offset
    """;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<CompanyListDto> findAll(CompanyFilter filter) {
        return namedParameterJdbcTemplate.query(FIND_ALL_QUERY, filteredFieldsMap(filter), this::buildCompanyListDto);
    }

    @SneakyThrows
    public CompanyListDto buildCompanyListDto(ResultSet rs, int rowNum) {
        return new CompanyListDto()
                .setId(rs.getLong("id"))
                .setCompanyName(rs.getString("company_name"))
                .setTaxNumber(rs.getString("tax_number"))
                .setIsGovernmentAgency(rs.getBoolean("is_government_agency"))
                .setCompanyStatus(CompanyStatus.valueOf(rs.getString("company_status")));
    }

    public MapSqlParameterSource filteredFieldsMap(CompanyFilter filter) {
        return new MapSqlParameterSource()
                .addValue("companyName", filter.getCompanyName())
                .addValue("taxNumber", filter.getTaxNumber())
                .addValue("userId", filter.getUserId())
                .addValue("isGovernmentAgency", filter.getIsGovernmentAgency())
                .addValue("companyStatus", filter.getCompanyStatus() == null ? null : filter.getCompanyStatus().toString())
                .addValue("limit", filter.getLimit())
                .addValue("offset", filter.getOffset());
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
