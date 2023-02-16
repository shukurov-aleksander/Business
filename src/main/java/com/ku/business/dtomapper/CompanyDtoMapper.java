package com.ku.business.dtomapper;

import com.ku.business.dto.company.CompanyDto;
import com.ku.business.dto.company.CompanyListDto;
import com.ku.business.dto.company.CompanySaveOrUpdateDto;
import com.ku.business.entity.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyDtoMapper {
    public Company fromDTOToEntity(CompanyDto companyDTO){
        return new Company(
                companyDTO.getId(),
                companyDTO.getCompanyName(),
                companyDTO.getTaxNumber(),
                companyDTO.isGovernmentAgency(),
                companyDTO.getUserId(),
                new StorageDtoMapper().fromDTOListToEntityList(companyDTO.getStorages()),
                new DetailDtoMapper().fromDTOListToEntityList(companyDTO.getDetails())
        );
    }

    public CompanyDto fromEntityToDTO(Company company){
        return new CompanyDto(
                company.getId(),
                company.getCompanyName(),
                company.getTaxNumber(),
                company.isGovernmentAgency(),
                company.getUserId(),
                new StorageDtoMapper().fromEntityListToDTOList(company.getStorages()),
                new DetailDtoMapper().fromEntityListToDTOList(company.getDetails())
        );
    }

    public CompanyListDto fromCompanyToCompanyListDTO(Company company) {
        return new CompanyListDto(
                company.getId(),
                company.getCompanyName(),
                company.getTaxNumber(),
                company.isGovernmentAgency()
        );
    }
    public Company fromCompanyListDTOtoCompany(CompanyListDto companyListDTO) {
        return new Company(
                companyListDTO.getId(),
                companyListDTO.getCompanyName(),
                companyListDTO.getTaxNumber(),
                companyListDTO.isGovernmentAgency(),
                null,
                null,
                null
        );
    }

    public List<Company> fromDTOListToEntityList(List<CompanyListDto> companiesDTO){
        List<Company> companies = new ArrayList<>();
        for (CompanyListDto companyListDTO : companiesDTO) {
            companies.add(fromCompanyListDTOtoCompany(companyListDTO));
        }
        return companies;
    }

    public List<CompanyListDto> fromEntityListToDTOList(List<Company> companies){
        List<CompanyListDto> companiesListDTO = new ArrayList<>();
        for (Company company : companies) {
            companiesListDTO.add(fromCompanyToCompanyListDTO(company));
        }
        return companiesListDTO;
    }

    public CompanySaveOrUpdateDto fromEntityToSaveOrUpdateDTO(Company company){
        return new CompanySaveOrUpdateDto(
                company.getId(),
                company.getCompanyName(),
                company.getTaxNumber(),
                company.isGovernmentAgency(),
                company.getUserId()
        );
    }
}
