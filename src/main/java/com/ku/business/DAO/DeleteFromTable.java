package com.ku.business.DAO;

import com.ku.business.controller.Connect;

import java.sql.SQLException;
import java.sql.Statement;

public class DeleteFromTable {
    public void deleteWordFromTable(String name, int id) {
        Connect connect = new Connect();
        Statement statement;
        String deleteWord = "DELETE FROM " + name + " WHERE id =" + id + ";";
        try {
            statement = connect.getConnection().createStatement();
            statement.execute(deleteWord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

