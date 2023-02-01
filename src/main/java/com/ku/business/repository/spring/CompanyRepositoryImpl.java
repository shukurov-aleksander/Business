package com.ku.business.repository.spring;

import com.ku.business.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
                )));
    }


//        (rs, rowNum) ->
//                Optional.of(new Company(
//                        rs.getLong("id"),
//                        rs.getString("company_name"),
//                        rs.getString("tax_number"),
//                        rs.getBoolean("is_government_agency"),
//                        rs.getLong("user_id")
//                ))
    //      return jdbcTemplate.query("""
    //              SELECT id, company_name, tax_number, is_government_agency, user_id
    //              FROM companies c
    //              WHERE c.id = ?
    //              """
    //                      ,new CompanyRowMapper(),id)
    //              .stream()
    //              .findFirst();


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

