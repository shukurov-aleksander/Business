package com.ku.business.dtomapper;

import com.ku.business.dto.CompanyDto;
import com.ku.business.dto.CompanyListDto;
import com.ku.business.dto.CompanySaveOrUpdateDto;
import com.ku.business.entity.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyDtoMapper implements Mapper<Company, CompanyDto, CompanyListDto, CompanySaveOrUpdateDto> {
    @Override
    public CompanyDto toDto(Company company) {
        return new CompanyDto(
                company.getId(),
                company.getCompanyName(),
                company.getTaxNumber(),
                company.isGovernmentAgency(),
                company.getUserId(),
                new StorageDtoMapper().toDtoList(company.getStorages()),
                new DetailDtoMapper().toDtoList(company.getDetails())
        );
    }

    @Override
    public CompanyListDto toListDto(Company company) {
        return new CompanyListDto(
                company.getId(),
                company.getCompanyName(),
                company.getTaxNumber(),
                company.isGovernmentAgency()
        );
    }

    @Override
    public List<CompanyListDto> toDtoList(List<Company> companies){
        List<CompanyListDto> companiesListDTO = new ArrayList<>();
        for (Company company : companies) {
            companiesListDTO.add(toListDto(company));
        }
        return companiesListDTO;
    }

    @Override
    public CompanySaveOrUpdateDto toSaveOrUpdateDto(Company company){
        return new CompanySaveOrUpdateDto(
                company.getId(),
                company.getCompanyName(),
                company.getTaxNumber(),
                company.isGovernmentAgency(),
                company.getUserId()
        );
    }

    @Override
    public Company fromSaveOrUpdateDto(CompanySaveOrUpdateDto companySaveOrUpdateDto){
        return new Company(
                companySaveOrUpdateDto.getId(),
                companySaveOrUpdateDto.getCompanyName(),
                companySaveOrUpdateDto.getTaxNumber(),
                companySaveOrUpdateDto.isGovernmentAgency(),
                companySaveOrUpdateDto.getUserId(),
                null,
                null
        );
    }
}
