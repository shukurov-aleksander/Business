package com.ku.business.service;

import com.ku.business.filter.CompanyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompanyService {
        private RestTemplate restTemplate;

    public String findAll(CompanyFilter filter) {
        StringBuilder findAllUrlBuilder = new StringBuilder("http://localhost:8080/companies/?");
        filter.getCompanyName().ifPresent(companyName -> findAllUrlBuilder.append("&companyName=").append(companyName));
        filter.getTaxNumber().ifPresent(taxNumber -> findAllUrlBuilder.append("&taxNumber=").append(taxNumber));
        filter.getUserId().ifPresent(userId -> findAllUrlBuilder.append("&userId=").append(userId));
        filter.getIsGovernmentAgency().ifPresent(isGovernmentAgency -> findAllUrlBuilder.append("&isGovernmentAgency=").append(isGovernmentAgency));
        filter.getCompanyStatus().ifPresent(companyStatus -> findAllUrlBuilder.append("&companyStatus=").append(companyStatus));
        filter.getOffset().ifPresent(offset -> findAllUrlBuilder.append("&offset=").append(offset));
        filter.getLimit().ifPresent(limit -> findAllUrlBuilder.append("&limit=").append(limit));
        return restTemplate.getForObject(findAllUrlBuilder.toString(), String.class);
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}