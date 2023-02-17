package com.ku.business.service.impl;

import com.ku.business.dto.CompanyDto;
import com.ku.business.dtomapper.CompanyDtoMapper;
import com.ku.business.repository.CompanyRepository;
import com.ku.business.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CrudService<CompanyDto> {
    @Autowired
    private CompanyRepository companyRepository;
    private final CompanyDtoMapper companyDtoMapper;

    public CompanyServiceImpl(CompanyDtoMapper companyDTOMapper) {
        this.companyDtoMapper = companyDTOMapper;
    }

    @Override
    public Optional<CompanyDto> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public List<CompanyDto> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public void save(CompanyDto company) {
        companyRepository.save(company);
    }

    @Override
    public void update(CompanyDto company) {
        companyRepository.save(company);
    }

    @Override
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
