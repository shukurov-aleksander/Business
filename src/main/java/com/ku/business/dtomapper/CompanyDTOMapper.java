package com.ku.business.dtomapper;

import com.ku.business.dto.company.CompanyDTO;
import com.ku.business.dto.company.CompanyListDTO;
import com.ku.business.dto.company.CompanySaveOrUpdateDTO;
import com.ku.business.entity.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyDTOMapper {
    public Company fromDTOToEntity(CompanyDTO companyDTO){
        return new Company(
                companyDTO.getId(),
                companyDTO.getCompanyName(),
                companyDTO.getTaxNumber(),
                companyDTO.isGovernmentAgency(),
                companyDTO.getUserId(),
                new StorageDTOMapper().fromDTOListToEntityList(companyDTO.getStorages()),
                new DetailDTOMapper().fromDTOListToEntityList(companyDTO.getDetails())
        );
    }

    public CompanyDTO fromEntityToDTO(Company company){
        return new CompanyDTO(
                company.getId(),
                company.getCompanyName(),
                company.getTaxNumber(),
                company.isGovernmentAgency(),
                company.getUserId(),
                new StorageDTOMapper().fromEntityListToDTOList(company.getStorages()),
                new DetailDTOMapper().fromEntityListToDTOList(company.getDetails())
        );
    }

    public CompanyListDTO fromCompanyToCompanyListDTO(Company company) {
        return new CompanyListDTO(
                company.getId(),
                company.getCompanyName(),
                company.getTaxNumber(),
                company.isGovernmentAgency()
        );
    }
    public Company fromCompanyListDTOtoCompany(CompanyListDTO companyListDTO) {
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

    public List<Company> fromDTOListToEntityList(List<CompanyListDTO> companiesDTO){
        List<Company> companies = new ArrayList<>();
        for (CompanyListDTO companyListDTO : companiesDTO) {
            companies.add(fromCompanyListDTOtoCompany(companyListDTO));
        }
        return companies;
    }

    public List<CompanyListDTO> fromEntityListToDTOList(List<Company> companies){
        List<CompanyListDTO> companiesListDTO = new ArrayList<>();
        for (Company company : companies) {
            companiesListDTO.add(fromCompanyToCompanyListDTO(company));
        }
        return companiesListDTO;
    }

    public CompanySaveOrUpdateDTO fromEntityToSaveOrUpdateDTO(Company company){
        return new CompanySaveOrUpdateDTO(
                company.getId(),
                company.getCompanyName(),
                company.getTaxNumber(),
                company.isGovernmentAgency(),
                company.getUserId()
        );
    }
}
