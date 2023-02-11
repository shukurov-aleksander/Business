package com.ku.business;

import com.ku.business.entity.Storage;
import com.ku.business.repository.spring.DatabaseConfig;
import com.ku.business.service.StorageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class BusinessApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        StorageService service = applicationContext.getBean(StorageService.class);
        List<Storage> storages = service.findAll();
        for (Storage storage: storages
             ) {
            System.out.println(storage);
        }
       System.out.println(service.findById(811L));
    }
}

