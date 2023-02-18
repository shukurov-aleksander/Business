package com.ku.business;

import com.ku.business.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BusinessApp implements CommandLineRunner {
    @Autowired
    CompanyServiceImpl service;
    public static void main(String[] args) {
        SpringApplication.run(BusinessApp.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        service.findById(4L);
    }
}

