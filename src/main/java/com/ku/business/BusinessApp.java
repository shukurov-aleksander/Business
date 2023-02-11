package com.ku.business;

import com.ku.business.entity.Company;
import com.ku.business.repository.spring.JavaConfig;
import com.ku.business.service.CompanyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class BusinessApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        CompanyService service = applicationContext.getBean(CompanyService.class);
        List<Company> companies = service.findAll();
        for (Company company: companies
             ) {
            System.out.println(company);
        }
       System.out.println(service.findById(811L));
    }
}

