package com.ku.business.service;

import com.ku.business.dto.CompanyListDto;
import com.ku.business.filter.CompanyFilter;
import com.ku.business.repository.CompanyDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyDao companyDao;

    public CompanyService(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public List<CompanyListDto> findAll(CompanyFilter filter) {
        return companyDao.findAll(filter);
    }
}
