package com.ku.business.service;

import com.ku.business.controller.Connect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUDOperations {
    public ResultSet CRUDOperationsCompleting(String sqlRequest) {
        Connect connect = new Connect();
        ResultSet resultSet = null;
        Statement statement;
        try {
            statement = connect.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlRequest);
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
        return resultSet;
    }
}
