package com.ku.business.repository.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.DriverManager;

@Component()
public class Connection {
    java.sql.Connection connection;
    String url = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String password = "postgres";
    String driver = "org.postgresql.Driver";
    @Bean
    public java.sql.Connection getConnection() throws Exception {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
}
