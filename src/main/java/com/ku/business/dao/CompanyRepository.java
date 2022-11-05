package com.ku.business.dao;

import com.ku.business.entity.Company;
import com.ku.business.exception.RepositoryException;

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
    final String SELECT_BY_ID = SELECT + " WHERE id= ?";
    final String DELETE_BY_ID = "DELETE FROM companies WHERE id = ?";
    final String INSERT_INTO = "INSERT INTO companies (company_name, tax_number, user_id, is_government_agency) VALUES (?, ?, ?, ?)";
    final String UPDATE = "UPDATE companies SET company_name = ?, tax_number =?, user_id = ?, is_government_agency =? WHERE id = ?";

    public CompanyRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Company findById(Long id) throws RepositoryException {
        Company company;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID))
        {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                company = createCompany(resultSet);
            }

        } catch (SQLException s) {
            throw new RepositoryException("Can't find user with id=" + id,s);
        }
        return company;
    }

        public List<Company> findAll() throws RepositoryException {
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
            throw new RepositoryException("Table is empty!",e);
        }
        return companies;
    }

    public void add(Company company) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO))
        {
            preparedStatement.setString(1,company.getCompanyName());
            preparedStatement.setString(2,company.getTaxNumber());
            preparedStatement.setLong(3,company.getUserId());
            preparedStatement.setBoolean(4,company.isGovernmentAgency());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Company with tax number=" + company.getTaxNumber() + " already exist",e);
        }
    }

    public void update(Company company) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE))
        {
            preparedStatement.setString(1,company.getCompanyName());
            preparedStatement.setString(2,company.getTaxNumber());
            preparedStatement.setLong(3,company.getUserId());
            preparedStatement.setBoolean(4,company.isGovernmentAgency());
            preparedStatement.setLong(5,company.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Can't find user with id=" + company.getId(),e);
        }
    }

    public void delete(Long id) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID))
        {
             preparedStatement.setLong(1,id);
             preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryException("Can't find user with id=" + id,e);
        }
    }

    private Company createCompany(ResultSet resultSet) throws RepositoryException {
        Company company = new Company();
        try {
            resultSet.next();
            company.setId(Long.valueOf(resultSet.getString("id")));
            company.setCompanyName(String.valueOf(resultSet.getString("company_name")));
            company.setTaxNumber(String.valueOf(resultSet.getString("tax_number")));
            company.setUserId(Long.valueOf(String.valueOf(resultSet.getString("user_id"))));
            company.setGovernmentAgency((String.valueOf(resultSet.getString("is_government_agency"))).equals("t"));
        } catch (SQLException s) {
            throw new RepositoryException("Result set is empty!",s);
        }
        return company;
    }
}
