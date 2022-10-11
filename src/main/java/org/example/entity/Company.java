package org.example.entity;

import java.util.Objects;

public class Company {
    private long id;
    private String companyName;
    private String taxNumber;
    private boolean isGovernmentAgency;

    public Company() {
    }

    public Company(long id, String companyName, String taxNumber, boolean isGovernmentAgency) {
        this.id = id;
        this.companyName = companyName;
        this.taxNumber = taxNumber;
        this.isGovernmentAgency = isGovernmentAgency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public boolean isGovernmentAgency() {
        return isGovernmentAgency;
    }

    public void setGovernmentAgency(boolean governmentAgency) {
        this.isGovernmentAgency = governmentAgency;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Company company = (Company) obj;
        return id == company.id &&
                isGovernmentAgency == company.isGovernmentAgency &&
                Objects.equals(companyName, company.companyName) &&
                taxNumber.equals(company.taxNumber);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int) (id - (id >>> 32));
        result = 31 * result + (isGovernmentAgency ? 0 : 1);
        result = 31 * result + (companyName == null ? 0 : companyName.hashCode());
        result = 31 * result + (taxNumber == null ? 0 : taxNumber.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Company [" +
                "id=" + id +
                ", companyName='" + companyName +
                "' , taxNumber='" + taxNumber +
                "' , isGovernmentAgency=" + isGovernmentAgency +
                "]";
    }
}
