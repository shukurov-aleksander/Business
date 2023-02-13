package com.ku.business;

import com.ku.business.config.JavaConfig;
import com.ku.business.service.impl.DocumentServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BusinessApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        DocumentServiceImpl service = applicationContext.getBean(DocumentServiceImpl.class);
//        List<Company> companies = service.findAll();
//        for (Company company: companies
//             ) {
//            System.out.println(company);
//        }

       System.out.println(service.findById(4L));
    }
}

