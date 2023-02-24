package com.ku.business.service;

import com.ku.business.dto.CompanyDto;
import com.ku.business.dto.CompanyListDto;
import com.ku.business.dto.CompanySaveDto;
import com.ku.business.dtomapper.CompanyDtoMapper;
import com.ku.business.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Optional<CompanyDto> findById(Long id) {
        return Optional.of(CompanyDtoMapper.toDto(companyRepository.findById(id).get()));
    }

    public List<CompanyListDto> findAll() {
        return CompanyDtoMapper.toListDto(companyRepository.findAll());
    }

    public void save(CompanySaveDto company) {
        companyRepository.save(CompanyDtoMapper.fromSaveDto(company));
    }

    public void update(CompanySaveDto company) {
        companyRepository.save(CompanyDtoMapper.fromSaveDto(company));
    }

    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
