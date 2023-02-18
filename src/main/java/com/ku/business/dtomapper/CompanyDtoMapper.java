package com.ku.business.dtomapper;

import com.ku.business.dto.CompanyDto;
import com.ku.business.dto.CompanyListDto;
import com.ku.business.dto.CompanySaveOrUpdateDto;
import com.ku.business.entity.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyDtoMapper {
    public static CompanyDto toDto(Company company) {
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

    public static CompanyListDto toListDto(Company company) {
        return new CompanyListDto(
                company.getId(),
                company.getCompanyName(),
                company.getTaxNumber(),
                company.isGovernmentAgency()
        );
    }

    public static List<CompanyListDto> toDtoList(List<Company> companies){
        List<CompanyListDto> companiesListDTO = new ArrayList<>();
        for (Company company : companies) {
            companiesListDTO.add(toListDto(company));
        }
        return companiesListDTO;
    }

    public static CompanySaveOrUpdateDto toSaveOrUpdateDto(Company company){
        return new CompanySaveOrUpdateDto(
                company.getId(),
                company.getCompanyName(),
                company.getTaxNumber(),
                company.isGovernmentAgency(),
                company.getUserId()
        );
    }

    public static Company fromSaveOrUpdateDto(CompanySaveOrUpdateDto companySaveOrUpdateDto){
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
