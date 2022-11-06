package com.ku.business.dao;

import com.ku.business.entity.Company;
import com.ku.business.exception.RepositoryException;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    private final PGSimpleDataSource dataSource;
    final String GET_ALL_QUERY = "SELECT * FROM companies";
    final String GET_ID_BY_QUERY = GET_ALL_QUERY + " WHERE id= ?";
    final String DELETE_ID_BY_QUERY = "DELETE FROM companies WHERE id = ?";
    final String INSERT_INTO_TABLE_QUERY = "INSERT INTO companies (company_name, tax_number, user_id, is_government_agency) VALUES (?, ?, ?, ?)";
    final String UPDATE_ID_BY_QUERY = "UPDATE companies SET company_name = ?, tax_number =?, user_id = ?, is_government_agency = ? WHERE id = ?";

    public CompanyRepository(PGSimpleDataSource dataSource) {
        this.dataSource = dataSource;
        this.dataSource.setServerNames(new String[] {
                "Local Business database"
        });
        this.dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres?characterEncoding=utf8");
        this.dataSource.setDatabaseName(this.dataSource.getDatabaseName());
        this.dataSource.setUser("postgres");
        this.dataSource.setPassword("postgres");
    }
    public Company findById(Long id) throws RepositoryException {
        Company company;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ID_BY_QUERY))
        {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                company = createCompany(resultSet);
            }
        } catch (Exception s) {
            throw new RepositoryException("Can't find user with id=" + id,s);
        }
        return company;
    }

        public List<Company> findAll() throws RepositoryException {
        List<Company> companies = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                companies.add(createCompany(resultSet));
            }
        } catch (Exception e) {
            throw new RepositoryException("Table companies is empty!",e);
        }
        return companies;
    }

    public void save(Company company) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_TABLE_QUERY)) {
            preparedStatement.setString(1,company.getCompanyName());
            preparedStatement.setString(2,company.getTaxNumber());
            preparedStatement.setLong(3,company.getUserId());
            preparedStatement.setBoolean(4,company.isGovernmentAgency());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException("Company with tax number=" + company.getTaxNumber() + " already exist",e);
        }
    }

    public void update(Company company) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ID_BY_QUERY)) {
            preparedStatement.setString(1,company.getCompanyName());
            preparedStatement.setString(2,company.getTaxNumber());
            preparedStatement.setLong(3,company.getUserId());
            preparedStatement.setBoolean(4,company.isGovernmentAgency());
            preparedStatement.setLong(5,company.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException("Can't find user with id=" + company.getId(),e);
        }
    }

    public void delete(Long id) throws RepositoryException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ID_BY_QUERY)) {
             preparedStatement.setLong(1,id);
             preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException("Can't find user with id=" + id,e);
        }
    }

    private Company createCompany(ResultSet resultSet) throws RepositoryException {
        Company company = new Company();
        try {
            resultSet.next();
            company.setId(resultSet.getLong("id"));
            company.setCompanyName(resultSet.getString("company_name"));
            company.setTaxNumber(resultSet.getString("tax_number"));
            company.setUserId(resultSet.getLong("user_id"));
            company.setGovernmentAgency((resultSet.getString("is_government_agency")).equals("t"));
        } catch (Exception s) {
            throw new RepositoryException("Result set is empty!",s);
        }
        return company;
    }
}

