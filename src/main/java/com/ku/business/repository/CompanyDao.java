package com.ku.business.repository;

import com.ku.business.dto.CompanyListDto;
import com.ku.business.entity.Company;
import com.ku.business.entity.CompanyStatus;
import com.ku.business.filter.CompanyFilter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

import static org.springframework.util.StringUtils.hasText;

@Repository
public class CompanyDao {
    private static final String FIND_ALL_QUERY = """
        SELECT c.id, c.company_name, c.tax_number, c.is_government_agency, c.user_id, cs.company_status
        FROM companies c
            LEFT JOIN company_statuses cs on c.deprecated_company_status_id = cs.id
        WHERE (:isCompanyNameNull OR c.company_name = :companyName)
            AND (:isTaxNumberNull OR c.tax_number = :taxNumber)
            AND (:isUserIdNull OR c.user_id = :userId)    
            AND (:isGovernmentAgencyNull OR c.is_government_agency = :isGovernmentAgency)
            AND (:isCompanyStatusNull OR cs.company_status = :companyStatus::company_status_enum)
        LIMIT :limit OFFSET :offset
    """;
    public static final String INSERT_QUERY = """
            INSERT INTO companies (company_name, tax_number, user_id, is_government_agency)
                            VALUES (:companyName, :taxNumber, :userId, :isGovernmentAgency)                
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
                .addValue("isCompanyNameNull", !hasText(filter.getCompanyName()))
                .addValue("companyName", filter.getCompanyName())
                .addValue("isTaxNumberNull", !hasText(filter.getTaxNumber()))
                .addValue("taxNumber", filter.getTaxNumber())
                .addValue("isUserIdNull", filter.getUserId() == null)
                .addValue("userId", filter.getUserId())
                .addValue("isGovernmentAgencyNull", filter.getIsGovernmentAgency() == null)
                .addValue("isGovernmentAgency", filter.getIsGovernmentAgency())
                .addValue("isCompanyStatusNull", filter.getCompanyStatus() == null)
                .addValue("companyStatus", Objects.toString(filter.getCompanyStatus()))
                .addValue("limit", filter.getLimit())
                .addValue("offset", filter.getOffset());
    }

    public void save(Company company) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("companyName", company.getCompanyName())
                .addValue("taxNumber", company.getTaxNumber())
                .addValue("userId", company.getUserId())
                .addValue("isGovernmentAgency", company.getIsGovernmentAgency());
        namedParameterJdbcTemplate.update(INSERT_QUERY, mapSqlParameterSource);
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
