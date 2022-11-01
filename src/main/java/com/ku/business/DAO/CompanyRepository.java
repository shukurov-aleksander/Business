package com.ku.business.DAO;

import com.ku.business.controller.Connect;
import com.ku.business.entity.Company;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    Connect connect = new Connect();
    DataSource dataSource = connect.getConnection();
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;

    public Company findById(Long id) {
        Company company = new Company();
        StringBuilder result = new StringBuilder("SELECT * FROM companies WHERE id=").append(id);
        try {
            Connection connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(String.valueOf(result));
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            company.setId(Long.valueOf(resultSet.getString("id")));
            company.setCompanyName(String.valueOf(resultSet.getString("company_name")));
            company.setTaxNumber(String.valueOf(resultSet.getString("tax_number")));
            company.setUserId(Long.valueOf(String.valueOf(resultSet.getString("user_id"))));
            company.setGovernmentAgency((String.valueOf(resultSet.getString("is_government_agency"))).equals("t"));


        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return company;
    }

    public List<Company> findAll() {
        List<Company> companies = new ArrayList<>();
        Company company = new Company();
        StringBuilder result = new StringBuilder("SELECT * FROM companies");
        try {
            Connection connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(String.valueOf(result));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                {
                    company.setId(Long.valueOf(resultSet.getString("id")));
                    company.setCompanyName(String.valueOf(resultSet.getString("company_name")));
                    company.setTaxNumber(String.valueOf(resultSet.getString("tax_number")));
                    company.setUserId(Long.valueOf(String.valueOf(resultSet.getString("user_id"))));
                    company.setGovernmentAgency((String.valueOf(resultSet.getString("is_government_agency"))).equals("t"));
                }
                companies.add(company);
                company = new Company();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return companies;
    }


    public void add(String companyName, String taxNumber, Long userId, Boolean isGovernmentAgency) {
        StringBuilder result = new StringBuilder("INSERT INTO companies (company_name, tax_number, user_id, is_government_agency) VALUES ('").append(companyName).append("', '").append(taxNumber).append("', ").append(userId).append(", '").append(isGovernmentAgency).append("')");
        try {
            Connection connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(String.valueOf(result));
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Company company) {
        StringBuilder updateCompany = new StringBuilder("UPDATE  companies SET company_name = '").append(company.getCompanyName()).append("', tax_number ='").append(company.getTaxNumber()).append("', user_id =").append(company.getUserId()).append(", is_government_agency ='").append(company.isGovernmentAgency()).append("' WHERE id =").append(company.getId()).append(";");
        try {
            Connection connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(String.valueOf(updateCompany));
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(Long id) {
        StringBuilder deleteFromCompany = new StringBuilder("DELETE FROM companies WHERE id =").append(id).append(";");
        try {
            Connection connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(String.valueOf(deleteFromCompany));
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
