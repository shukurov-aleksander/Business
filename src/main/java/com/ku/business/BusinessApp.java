package com.ku.business;

import com.ku.business.repository.spring.jdbc.CompanyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class BusinessApp {
    public static void main(String[] args) {
        SpringApplication.run(BusinessApp.class, args);
    }

        @Bean
        CommandLineRunner commandLineRunner (CompanyRepository companyRepository){
            return args -> {
                companyRepository.findById(811L);
            };
        }
    }

