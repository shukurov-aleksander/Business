package com.ku.business.DAO;

import com.ku.business.controller.Connect;
import com.ku.business.entity.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetFromTable {
    public String selectFromTableCompany(String name, String where, String... column) {
        Connect connect = new Connect();
        ResultSet resultSet = null;
        Statement statement;
        StringBuilder result = new StringBuilder("");
        StringBuilder showTable = new StringBuilder("SELECT * FROM ").append(name).append(" ").append("WHERE id =").append(where).append(";");
        try {
            statement = connect.getConnection().createStatement();
            resultSet = statement.executeQuery(String.valueOf(showTable));
            while (resultSet.next()) {
                for (String c : column
                ) {
                    result.append(" ").append(resultSet.getString(c)).append(" ");
                }
                result.append("\n");
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
        return String.valueOf(result);
    }

    public List<Company> selectFromTableCompany(String name) {
        Connect connect = new Connect();
        List<Company> companies = new ArrayList<>();
        Company company = new Company();
        ResultSet resultSet = null;
        Statement statement;
        StringBuilder result = new StringBuilder("SELECT ").append("* ").append("FROM ").append(name);
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
}

