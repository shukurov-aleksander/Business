package com.ku.business.dao;

import com.ku.business.entity.Company;
import com.ku.business.exception.MyOwnException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    private final DataSource dataSource;
    final String SELECT = "SELECT * FROM companies";
    final String SELECT_BY_ID = SELECT + " WHERE id=";
    final String DELETE_BY_ID = "DELETE FROM companies WHERE id =";
    final String INSERT_INTO = "INSERT INTO";
    final String UPDATE = "UPDATE";

    public CompanyRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Company findById(Long id) {
        Company company = new Company();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID + id);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            company = createCompany(resultSet);
        } catch (SQLException s) {
            throw new MyOwnException(s);
        }
        return company;
    }

    public List<Company> findAll() {
        List<Company> companies = new ArrayList<>();
        Company company;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next()) {
                company = createCompany(resultSet);
                companies.add(company);
            }
        } catch (SQLException e) {
            throw new MyOwnException(e);
        }
        return companies;
    }

    public void add(Company company) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(requestForInsertInDb(company)))
        {
             preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new MyOwnException(e);
        }
    }

    public void update(Company company) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(requestForUpdateById(company)))
        {
             preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new MyOwnException(e);
        }
    }

    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID + id))
        {
             preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new MyOwnException(e);
        }
    }

    private Company createCompany(ResultSet resultSet) {
        Company company = new Company();
        try {
            resultSet.next();
            company.setId(Long.valueOf(resultSet.getString("id")));
            company.setCompanyName(String.valueOf(resultSet.getString("company_name")));
            company.setTaxNumber(String.valueOf(resultSet.getString("tax_number")));
            company.setUserId(Long.valueOf(String.valueOf(resultSet.getString("user_id"))));
            company.setGovernmentAgency((String.valueOf(resultSet.getString("is_government_agency"))).equals("t"));
        } catch (SQLException s) {
            throw new MyOwnException(s);
        }
        return company;
    }

    public String requestForInsertInDb(Company company) {
        return INSERT_INTO + " companies (company_name, tax_number, user_id, is_government_agency) VALUES ('" +
                company.getCompanyName() + "', '" + company.getTaxNumber() + "', " + company.getUserId() +
                ", '" + company.isGovernmentAgency() + "')";
    }

    public String requestForUpdateById(Company company) {
        return UPDATE + (" companies SET company_name = '") + company.getCompanyName() + "', tax_number ='" +
                company.getTaxNumber() + "', user_id =" + company.getUserId() + ", is_government_agency ='" +
                company.isGovernmentAgency() + "' WHERE id =" + company.getId() + ";";
    }
}
