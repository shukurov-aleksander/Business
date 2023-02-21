package com.ku.business.dtomapper;

import com.ku.business.dto.CompanyDto;
import com.ku.business.dto.CompanyListDto;
import com.ku.business.dto.CompanySaveOrUpdateDto;
import com.ku.business.entity.Company;

import java.util.HashSet;
import java.util.Set;

public class CompanyDtoMapper {
    public static CompanyDto toDto(Company company) {
        return new CompanyDto()
                .setId(company.getId())
                .setCompanyName(company.getCompanyName())
                .setTaxNumber(company.getTaxNumber())
                .setIsGovernmentAgency(company.getIsGovernmentAgency())
                .setUserId(company.getUserId())
                .setDetails(new DetailDtoMapper().toListDto(company.getDetails()))
                .setStorages(new StorageDtoMapper().toListDto(company.getStorages())
        );
    }

    public static CompanyListDto toListDto(Company company) {
        return new CompanyListDto().setId(company.getId())
                .setCompanyName(company.getCompanyName())
                .setTaxNumber(company.getTaxNumber())
                .setIsGovernmentAgency(company.getIsGovernmentAgency()
        );
    }

    public static Set<CompanyListDto> toListDto(Set<Company> companies){
        Set<CompanyListDto> companiesListDTO = new HashSet<>();
        for (Company company : companies) {
            companiesListDTO.add(toListDto(company));
        }
        return companiesListDTO;
    }

    public static CompanySaveOrUpdateDto toSaveOrUpdateDto(Company company){
        return new CompanySaveOrUpdateDto()
                .setId(company.getId())
                .setCompanyName(company.getCompanyName())
                .setTaxNumber(company.getTaxNumber())
                .setIsGovernmentAgency(company.getIsGovernmentAgency())
                .setUserId(company.getUserId()
        );
    }

    public static Company fromSaveOrUpdateDto(CompanySaveOrUpdateDto companySaveOrUpdateDto){
        return new Company()
                .setId(companySaveOrUpdateDto.getId())
                .setCompanyName(companySaveOrUpdateDto.getCompanyName())
                .setTaxNumber(companySaveOrUpdateDto.getTaxNumber())
                .setIsGovernmentAgency(companySaveOrUpdateDto.getIsGovernmentAgency())
                .setUserId(companySaveOrUpdateDto.getUserId()

        );
    }
}
