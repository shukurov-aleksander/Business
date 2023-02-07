package com.ku.business.repository.spring.jdbc;

import com.ku.business.entity.Company;
import com.ku.business.entity.Detail;
import com.ku.business.entity.Storage;
import com.ku.business.exception.RepositoryException;
import com.ku.business.repository.spring.jdbc.rowmapper.CompanyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    // left join storages s on c.id = s.company_id
// left join details d on c.id = d.company_id
    public Optional<Company> findCompany(Long id) {
        Company company = new Company();
        Collection<Storage> storages = new ArrayList<>();
        Collection<Detail> details = new ArrayList<>();
        return jdbcTemplate.queryForObject("""
                SELECT * FROM companies c WHERE c.id = ?
                        """, (rs, rowNum) -> Optional.of(
                company.setCompanyName(rs.getString("company_name")),
                company.setTaxNumber(rs.getString("tax_number")),
                company.setGovernmentAgency(rs.getBoolean("is_government_agency")),
                company.setUserId(rs.getLong("user_id")),
            Storage storage = new Storage(rs.getLong("s.id"));
            storages.add(storage);
            Detail detail = new Detail(rs.getLong("d.id"));
            details.add(detail);
        }
        if (company == null) {
            throw new RepositoryException("Company not found");
        }), id);
    }


    @Override
    public Optional<Company> findById(Long id) {
        return jdbcTemplate.queryForObject("""
                  SELECT c.id, c.company_name, c.tax_number, c.is_government_agency, c.user_id
                                FROM companies c
                                left join storages s on c.id = s.company_id
                                left join details d on c.id = d.company_id
                                WHERE c.id = ?    
                """, (rs, rowNum) ->
                Optional.of(new Company(
                        rs.getLong("id"),
                        rs.getString("company_name"),
                        rs.getString("tax_number"),
                        rs.getBoolean("is_government_agency"),
                        rs.getLong("user_id")
                )),id);
    }

    @Override
    public List<Company> findAll() {
        return jdbcTemplate.query(
                "select * from companies",
                (rs, rowNum) ->
                        new Company(
                                rs.getLong("id"),
                                rs.getString("company_name"),
                                rs.getString("tax_number"),
                                rs.getBoolean("is_government_agency"),
                                rs.getLong("user_id")
                        )
        );
    }

    @Override
    public void save(Company company) {
        jdbcTemplate.update(
                "INSERT INTO companies (company_name, tax_number, user_id, is_government_agency) VALUES(?, ?, ?, ?)",
                company.getCompanyName(), company.getTaxNumber(), company.getUserId(), company.isGovernmentAgency());
    }

    @Override
    public void update(Company company) {
        jdbcTemplate.update("UPDATE companies SET company_name = ?, tax_number = ?, user_id = ?, is_government_agency = ? WHERE id = ?",
                company.getCompanyName(), company.getTaxNumber(), company.getUserId(), company.isGovernmentAgency(), company.getId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("delete from companies where id = ?", id);
    }

}

