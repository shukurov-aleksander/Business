package com.ku.business.repository;

import com.ku.business.entity.Company;
import com.ku.business.entity.CompanyStatus;
import com.ku.business.filter.CompanyFilter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Repository
public class CompanyRepository {
    private static final String QUERY_FIND_ALL_WITH_FILTER = """
        SELECT 
            c.id, 
            c.company_name as company_name, 
            c.tax_number as tax_number, 
            c.is_government_agency as is_government_agency, 
            c.user_id as user_id, 
            cs.company_status as company_status
        FROM companies c
        LEFT JOIN company_statuses cs on c.company_status_id = cs.id
        WHERE (:isCompanyNameNull OR c.company_name = :companyName)
            AND (:isTaxNumberNull OR c.tax_number = :taxNumber)
            AND (:isUserIdNull OR c.user_id = :userId)    
            AND (:isGovernmentAgencyNull OR c.is_government_agency = :isGovernmentAgency)
            AND (:isCompanyStatusNull OR cs.company_status = :companyStatus::company_status_enum)
        LIMIT :limit OFFSET :offset
    """;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Company> findAll(CompanyFilter filter) {
        return namedParameterJdbcTemplate.query(
                QUERY_FIND_ALL_WITH_FILTER,
                createMapOfFilteredFields(filter),
                new ResultSetExtractor<List<Company>>() {
            @Override
            public List<Company> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Company> companies = new ArrayList<>();
                while (rs.next()){
                   companies.add(createCompanyFromResultSet(rs));
                }
                return companies;
            }
        });
    }

    @SneakyThrows
    public Company createCompanyFromResultSet(ResultSet rs) {
        return new Company()
                .setId(rs.getLong("id"))
                .setCompanyName(rs.getString("company_name"))
                .setTaxNumber(rs.getString("tax_number"))
                .setIsGovernmentAgency(rs.getBoolean("is_government_agency"))
                .setUserId(rs.getLong("user_id"))
                .setCompanyStatus(CompanyStatus.valueOf(rs.getString("company_status")));
    }

    public MapSqlParameterSource createMapOfFilteredFields(CompanyFilter filter) {
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
                .addValue("companyStatus", filter.getCompanyStatus() != null ? filter.getCompanyStatus().toString() : "")
                .addValue("limit", filter.getLimit())
                .addValue("offset", filter.getOffset());
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
