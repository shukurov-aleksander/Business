package com.ku.business.repository.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
@ComponentScan("com.ku.business")
@PropertySource("application.properties")
public class JavaConfig {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.application.name}")
    private String user;
    @Value("${spring.application.password}")
    private String password;
    @Value("${spring.datasource.driverClassName}")
    private String driver;

    @Bean
    public java.sql.Connection getConnection() throws Exception {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }
}
