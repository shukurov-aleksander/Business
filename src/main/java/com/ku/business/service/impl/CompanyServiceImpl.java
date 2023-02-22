package com.ku.business.service.impl;

import com.ku.business.dto.CompanyDto;
import com.ku.business.dto.CompanyListDto;
import com.ku.business.dto.CompanySaveDto;
import com.ku.business.dtomapper.CompanyDtoMapper;
import com.ku.business.repository.CompanyRepository;
import com.ku.business.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CompanyServiceImpl implements CrudService<CompanyDto, CompanyListDto, CompanySaveDto> {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Optional<CompanyDto> findById(Long id) {
        return Optional.of(CompanyDtoMapper.toDto(companyRepository.findById(id).get()));
    }

    @Override
    public Set<CompanyListDto> findAll() {
        return CompanyDtoMapper.toListDto(companyRepository.findAll());
    }

    @Override
    public void save(CompanySaveDto company) {
        companyRepository.save(CompanyDtoMapper.fromSaveDto(company));
    }

    @Override
    public void update(CompanySaveDto company) {
        companyRepository.save(CompanyDtoMapper.fromSaveDto(company));
    }

    @Override
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
