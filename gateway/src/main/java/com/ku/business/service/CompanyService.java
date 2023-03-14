package com.ku.business.service;

import com.ku.business.filter.CompanyFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompanyService {

    public String findAll(CompanyFilter filter) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = new StringBuilder("http://localhost:8080/companies?")
            .append(filter.getCompanyName() != null ? new StringBuilder("&companyName=").append(filter.getCompanyName()) : "")
            .append(filter.getTaxNumber() != null ? new StringBuilder("&taxNumber=").append(filter.getTaxNumber()) : "")
            .append(filter.getUserId() != null ? new StringBuilder("&userId=").append(filter.getUserId()) : "")
            .append(filter.getIsGovernmentAgency() != null ? new StringBuilder("&isGovernmentAgency=").append(filter.getIsGovernmentAgency()) : "")
            .append(filter.getCompanyStatus() != null ? new StringBuilder("&companyStatus=").append(filter.getCompanyStatus()) : "")
            .append(filter.getOffset() != null ? new StringBuilder("&offset=").append(filter.getOffset()) : "")
            .append(filter.getLimit() != null ? new StringBuilder("&limit=").append(filter.getLimit()) : "").toString();
        return restTemplate.getForObject(fooResourceUrl, String.class);
    }
}