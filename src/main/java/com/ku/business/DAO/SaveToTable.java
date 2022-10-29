package com.ku.business.DAO;

import com.ku.business.controller.Connect;

import java.sql.SQLException;
import java.sql.Statement;

public class SaveToTable {
    public void insertCompanyInTable(String name, Long id, String companyName, String taxNumber, String userId, Boolean isGovernmentAgency) {
        Connect connect = new Connect();
        Statement statement;
        String addCompany = "INSERT INTO " + name + " ( id, company_name, tax_number, user_id , is_government_agency ) VALUES ('" + id + "', '" + companyName + "', '" + taxNumber+ "', '" + userId+ "', '" + isGovernmentAgency + "');";
        try {
            statement = connect.getConnection().createStatement();
            statement.execute(addCompany);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}