package com.ku.business.dto;

public class CompanyListDto {
    private Long id;
    private String companyName;
    private String taxNumber;
    private Boolean isGovernmentAgency;

    public CompanyListDto(
        Long id,
        String companyName,
        String taxNumber,
        Boolean isGovernmentAgency
    ) {
        this.id = id;
        this.companyName = companyName;
        this.taxNumber = taxNumber;
        this.isGovernmentAgency = isGovernmentAgency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public Boolean isGovernmentAgency() {
        return isGovernmentAgency;
    }

    public void setGovernmentAgency(Boolean governmentAgency) {
        isGovernmentAgency = governmentAgency;
    }
}
