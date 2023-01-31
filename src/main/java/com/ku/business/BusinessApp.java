package com.ku.business;

import com.ku.business.entity.Company;
import com.ku.business.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class BusinessApp implements CommandLineRunner {
    @Autowired
    CompanyService companyService;
    public static void main(String[] args) {
        SpringApplication.run(BusinessApp.class, args);

    }
    @Override
    public void run(String... args) {
        System.out.println("StartApplication...");
        test();
    }
    void test() {
        Optional<Company> company = companyService.findById(2L);
        System.out.println(company);
    }
}
