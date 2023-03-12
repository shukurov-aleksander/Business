package com.ku.business.service;

import com.ku.business.dto.CompanyListDto;
import com.ku.business.filter.CompanyFilter;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    public List<CompanyListDto> findAll(CompanyFilter filter) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/companies/get_compnies";
        HttpEntity<List<CompanyListDto>> request = new HttpEntity<>(new ArrayList<>());
        List<CompanyListDto> listDtos = restTemplate.getForObject(fooResourceUrl, List.class);
        return listDtos;
    }
}
