package com.ku.business.DAO;

import com.ku.business.controller.Connect;
import com.ku.business.entity.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    public Company findById(Long id) {
        Connect connect = new Connect();
        Company company = new Company();
        ResultSet resultSet = null;
        Statement statement;
        StringBuilder result = new StringBuilder("SELECT * FROM companies WHERE id=").append(id);
        try {
            statement = connect.getConnection().createStatement();
            resultSet = statement.executeQuery(String.valueOf(result));
            while (resultSet.next()) {
                {
                    company.setId(Long.valueOf(resultSet.getString("id")));
                    company.setCompanyName(String.valueOf(resultSet.getString("company_name")));
                    company.setTaxNumber(String.valueOf(resultSet.getString("tax_number")));
                    company.setUserId(Long.valueOf(String.valueOf(resultSet.getString("user_id"))));
                    company.setGovernmentAgency((String.valueOf(resultSet.getString("is_government_agency"))).equals("t"));
                }
            }
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
        Connect connect = new Connect();
        List<Company> companies = new ArrayList<>();
        Company company = new Company();
        ResultSet resultSet = null;
        Statement statement;
        StringBuilder result = new StringBuilder("SELECT * FROM companies");
        try {
            statement = connect.getConnection().createStatement();
            resultSet = statement.executeQuery(String.valueOf(result));
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
        Connect connect = new Connect();
        Statement statement;
        StringBuilder result = new StringBuilder("INSERT INTO companies (company_name, tax_number, user_id, is_government_agency) VALUES ('")
                .append(companyName).append("', '")
                .append(taxNumber).append("', ")
                .append(userId).append(", '")
                .append(isGovernmentAgency).append("')");
        try {
            statement = connect.getConnection().createStatement();
            statement.execute(String.valueOf(result));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Company company) {
        Connect connect = new Connect();
        Statement statement;
        StringBuilder updateCompany = new StringBuilder("UPDATE  companies SET company_name = '").append(company.getCompanyName())
                .append("', tax_number ='").append(company.getTaxNumber())
                .append("', user_id =").append(company.getUserId())
                .append(", is_government_agency ='").append(company.isGovernmentAgency())
                .append("' WHERE id =").append(company.getId()).append(";");
        try {
            statement = connect.getConnection().createStatement();
            statement.execute(String.valueOf(updateCompany));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(Long id) {
        Connect connect = new Connect();
        Statement statement;
        StringBuilder deleteFromCompany = new StringBuilder("DELETE FROM companies WHERE id =").append(id).append(";");
        try {
            statement = connect.getConnection().createStatement();
            statement.execute(String.valueOf(deleteFromCompany));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
