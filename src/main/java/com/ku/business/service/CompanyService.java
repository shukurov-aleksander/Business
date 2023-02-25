package com.ku.business.service;

import com.ku.business.dto.CompanyListDto;
import com.ku.business.dtomapper.CompanyDtoMapper;
import com.ku.business.filter.CompanyFilter;
import com.ku.business.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyListDto> findAll(CompanyFilter filter) {
        return CompanyDtoMapper.toListDto(companyRepository.findAll(filter));
    }
}
