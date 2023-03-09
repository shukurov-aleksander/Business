package com.ku.business.service;

import com.ku.business.dto.CompanyListDto;
import com.ku.business.dto.CompanySaveDto;
import com.ku.business.dtomapper.CompanyDtoMapper;
import com.ku.business.entity.CompanyStatus;
import com.ku.business.filter.CompanyFilter;
import com.ku.business.repository.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyDao companyDao;
    private CompanyStatusService companyStatusService;

    public List<CompanyListDto> findAll(CompanyFilter filter) {
        return companyDao.findAll(filter);
    }

    public void save(CompanySaveDto companySaveDto) {
        Long id = companyDao.save(CompanyDtoMapper.fromSaveDto(companySaveDto));
        companyStatusService.save(id, CompanyStatus.REGISTERED);
    }

    @Autowired
    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Autowired
    public void setCompanyStatusService(CompanyStatusService companyStatusService) {
        this.companyStatusService = companyStatusService;
    }
}