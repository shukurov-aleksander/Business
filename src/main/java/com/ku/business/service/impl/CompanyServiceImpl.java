package com.ku.business.service.impl;

import com.ku.business.dto.company.CompanyDTO;
import com.ku.business.dtomapper.CompanyDTOMapper;
import com.ku.business.repository.CompanyRepository;
import com.ku.business.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CrudService<CompanyDTO> {
    @Autowired
    private CompanyRepository companyRepository;
    private final CompanyDTOMapper companyDTOMapper;

    public CompanyServiceImpl(CompanyDTOMapper companyDTOMapper) {
        this.companyDTOMapper = companyDTOMapper;
    }

    @Override
    public Optional<CompanyDTO> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public List<CompanyDTO> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public void save(CompanyDTO company) {
        companyRepository.save(company);
    }

    @Override
    public void update(CompanyDTO company) {
        companyRepository.save(company);
    }

    @Override
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
