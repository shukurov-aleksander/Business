package com.ku.business.service;

import com.ku.business.dto.CompanyDto;
import com.ku.business.dto.CompanyListDto;
import com.ku.business.exception.CompanyNotFoundException;
import com.ku.business.filter.CompanyFilter;
import com.ku.business.repository.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyDao companyDao;

    public List<CompanyListDto> findAll(CompanyFilter filter) {
        return companyDao.findAll(filter);
    }

    public CompanyDto findById(Long id) {
        try {
            return  companyDao.findById(id);
        } catch (UncategorizedSQLException exception) {
            throw new CompanyNotFoundException("Company was not found", exception, HttpStatus.NOT_FOUND);
        }
    }
    @Autowired
    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }
}