package com.ku.business.service;

import com.ku.business.entity.Company;
import com.ku.business.repository.CompanyStatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyStatusHistoryService {
    private CompanyStatusDao companyStatusDao;

    public void save(Company company) {
        companyStatusDao.save(company);
    }

    public void update(Company company) {
        companyStatusDao.save(company);
    }
    @Autowired
    public void setCompanyStatusDao(CompanyStatusDao companyStatusDao) {
        this.companyStatusDao = companyStatusDao;
    }
}
