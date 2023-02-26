package com.ku.business.repository;

import com.ku.business.entity.Company;
import com.ku.business.filter.CompanyFilter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Repository
public class CompanyRepository {
    private static final String QUERY_FIND_ALL_WITH_FILTER = """
        SELECT *
        FROM companies c
        WHERE (:isCompanyNameNull OR c.company_name = :companyName)
            AND (:isTaxNumberNull OR c.tax_number = :taxNumber)
            AND (:isUserIdNull OR c.user_id = :userId)    
            AND (:isGovernmentAgencyNull OR c.is_government_agency = :isGovernmentAgency)
        LIMIT :limit OFFSET :offset
    """;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Company> findAll(CompanyFilter filter) {
        return namedParameterJdbcTemplate.query(QUERY_FIND_ALL_WITH_FILTER, createMapOfFilteredFields(filter), (rs, rowNum) -> createCompanyFromResultSet(rs));
    }

    @SneakyThrows
    public Company createCompanyFromResultSet(ResultSet rs) {
        return new Company()
                .setId(rs.getLong("id"))
                .setCompanyName(rs.getString("company_name"))
                .setTaxNumber(rs.getString("tax_number"))
                .setIsGovernmentAgency(rs.getBoolean("is_government_agency"))
                .setUserId(rs.getLong("user_id"));
    }

    public MapSqlParameterSource createMapOfFilteredFields(CompanyFilter filter) {
       return new MapSqlParameterSource()
               .addValue("isCompanyNameNull",  !hasText(filter.getCompanyName()))
               .addValue("companyName", filter.getCompanyName())
               .addValue("isTaxNumberNull", !hasText(filter.getTaxNumber()))
               .addValue("taxNumber", filter.getTaxNumber())
               .addValue("isUserIdNull", filter.getUserId() == null)
               .addValue("userId", filter.getUserId())
               .addValue("isGovernmentAgencyNull", filter.getIsGovernmentAgency() == null)
               .addValue("isGovernmentAgency", filter.getIsGovernmentAgency())
               .addValue("limit", filter.getLimit())
               .addValue("offset", filter.getOffset());
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
