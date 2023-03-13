package com.ku.business.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CompanyService {

    public String findAll() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/companies/";
        String dns =  restTemplate.getForObject(fooResourceUrl, String.class);
        return dns;
    }
}
