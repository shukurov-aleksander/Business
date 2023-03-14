package com.ku.business.service;

import com.ku.business.filter.CompanyFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompanyService {

    public String findAll(CompanyFilter filter) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder fooResourceUrl = new StringBuilder("http://localhost:8080/companies?");
        if (filter.getCompanyName() != null && !filter.getCompanyName().equals("")) {
            fooResourceUrl.append("companyName=").append(filter.getCompanyName());
        }
        if (filter.getTaxNumber() != null && !filter.getTaxNumber().equals("")) {
            fooResourceUrl.append("&taxNumber=").append(filter.getTaxNumber());
        }
        if (filter.getUserId() != null) {
            fooResourceUrl.append("&userId=").append(filter.getUserId());
        }
        if (filter.getIsGovernmentAgency() != null) {
            fooResourceUrl.append("&isGovernmentAgency=").append(filter.getIsGovernmentAgency());
        }
        if (filter.getCompanyStatus() != null) {
            fooResourceUrl.append("&companyStatus=").append(filter.getCompanyStatus());
        }
        if (filter.getOffset() != null) {
            fooResourceUrl.append("&offset=").append(filter.getOffset());
        }
        if (filter.getLimit() != null) {
            fooResourceUrl.append("&limit=").append(filter.getLimit());
        }
        return restTemplate.getForObject(fooResourceUrl.toString(), String.class);
    }
}
