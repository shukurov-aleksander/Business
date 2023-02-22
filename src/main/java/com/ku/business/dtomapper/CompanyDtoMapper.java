package com.ku.business.dtomapper;

import com.ku.business.dto.CompanyDto;
import com.ku.business.dto.CompanyListDto;
import com.ku.business.dto.CompanySaveDto;
import com.ku.business.entity.Company;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CompanyDtoMapper {
    public static CompanyDto toDto(Company company) {
        return new CompanyDto()
                .setId(company.getId())
                .setCompanyName(company.getCompanyName())
                .setTaxNumber(company.getTaxNumber())
                .setIsGovernmentAgency(company.getIsGovernmentAgency())
                .setUserId(company.getUserId())
                .setDetails(DetailDtoMapper.toListDto(company.getDetails()))
                .setStorages(StorageDtoMapper.toListDto(company.getStorages())
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

    public static Set<CompanyListDto> toListDto(List<Company> companies){
        Set<CompanyListDto> companiesListDTO = new HashSet<>();
        for (Company company : companies) {
            companiesListDTO.add(toListDto(company));
        }
        return companiesListDTO;
    }

    public static CompanySaveDto toSaveDto(Company company){
        return new CompanySaveDto()
                .setId(company.getId())
                .setCompanyName(company.getCompanyName())
                .setTaxNumber(company.getTaxNumber())
                .setIsGovernmentAgency(company.getIsGovernmentAgency())
                .setUserId(company.getUserId()
        );
    }

    public static Company fromSaveDto(CompanySaveDto companySaveDto){
        return new Company()
                .setId(companySaveDto.getId())
                .setCompanyName(companySaveDto.getCompanyName())
                .setTaxNumber(companySaveDto.getTaxNumber())
                .setIsGovernmentAgency(companySaveDto.getIsGovernmentAgency())
                .setUserId(companySaveDto.getUserId()

        );
    }
}
