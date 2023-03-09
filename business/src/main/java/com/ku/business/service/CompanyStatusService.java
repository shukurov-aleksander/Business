package com.ku.business.service;

import com.ku.business.entity.CompanyStatus;
import com.ku.business.repository.CompanyStatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyStatusService {
    private CompanyStatusDao companyStatusDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Long companyId, CompanyStatus companyStatus) {
        if (companyStatusDao.updateStatus(companyId, companyStatus) != 0) {
            companyStatusDao.updateStatus(companyId, companyStatus);
        }
    }

    @Autowired
    public void setCompanyStatusDao(CompanyStatusDao companyStatusDao) {
        this.companyStatusDao = companyStatusDao;
    }
}