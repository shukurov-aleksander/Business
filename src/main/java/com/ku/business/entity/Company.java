package com.ku.business.entity;

import java.util.List;

public class Company {
    private Long id;
    private String companyName;
    private String taxNumber;
    private Boolean isGovernmentAgency;
    private Long userId;
    private List<Storage> storages;
    private List<Detail> details;

    public Company() {
    }

    public Company(
            Long id,
            String companyName,
            String taxNumber,
            Boolean isGovernmentAgency,
            Long userId,
            List<Storage> storages,
            List<Detail> details
    ) {
        this.id = id;
        this.companyName = companyName;
        this.taxNumber = taxNumber;
        this.isGovernmentAgency = isGovernmentAgency;
        this.userId = userId;
        this.storages = storages;
        this.details = details;
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
        this.isGovernmentAgency = governmentAgency;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Storage> getStorages() {
        return storages;
    }

    public void setStorages(List<Storage> storages) {
        this.storages = storages;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        Company aThat = (Company) obj;
        if ((this.id == null && aThat.id != null) || (this.id != null && aThat.id == null)) {return false;}
        if ((this.companyName == null && aThat.companyName != null) || (this.companyName != null && aThat.companyName == null)) {return false;}
        if ((this.taxNumber == null && aThat.taxNumber != null) || (this.taxNumber != null && aThat.taxNumber == null)) {return false;}
        if ((this.isGovernmentAgency == null && aThat.isGovernmentAgency != null) || (this.isGovernmentAgency != null && aThat.isGovernmentAgency == null)) {return false;}
        if ((this.userId == null && aThat.userId != null) || (this.userId != null && aThat.userId == null)) {return false;}
        if ((this.storages == null && aThat.storages != null) || (this.storages != null && aThat.storages == null)) {return false;}
        if ((this.details == null && aThat.details != null) || (this.details != null && aThat.details == null)) {return false;}
        if ((this.storages != null && aThat.storages != null) && (this.storages.size() == aThat.storages.size())) {
            for (int i = 0; i < storages.size(); i++) {
                if (!storages.get(0).getId().equals(aThat.storages.get(0).getId()))
                {return false;}
            }
        }
        if ((this.details != null && aThat.details != null) && (this.details.size() == aThat.details.size())) {
            for (int i = 0; i < details.size(); i++) {
                if (!details.get(0).getId().equals(aThat.details.get(0).getId()))
                {return false;}
            }
        }
        return (((this.id == aThat.id) && (aThat.id == null)) || (this.id.equals(aThat.id))) &&
                (((this.companyName == aThat.companyName) && (aThat.companyName == null)) || (this.companyName.equals(aThat.companyName))) &&
                (((this.taxNumber == aThat.taxNumber) && (aThat.taxNumber == null)) || (this.taxNumber.equals(aThat.taxNumber))) &&
                (((this.isGovernmentAgency == aThat.isGovernmentAgency) && (aThat.isGovernmentAgency == null)) || (this.isGovernmentAgency.equals(aThat.isGovernmentAgency))) &&
                (((this.userId == aThat.userId) && (aThat.userId == null)) || (this.userId.equals(aThat.userId)));
    }




    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (isGovernmentAgency == null ? 0 : (isGovernmentAgency ? 0 : 1));
        result = prime * result + (companyName == null ? 0 : companyName.hashCode());
        result = prime * result + (taxNumber == null ? 0 : taxNumber.hashCode());
        result = prime * result + (userId == null ? 0 : userId.hashCode());
        if(storages!=null) {
            for (Storage storage : storages
                 ) {
                result = prime * result + (storage!=null && storage.getId()!=null ?  (storage.getId().hashCode()) : 0);
            }
        }
        if(details!=null) {
            for (Detail detail : details
            ) {
                result = prime * result + (detail!=null && detail.getId()!=null ?  (detail.getId().hashCode()) : 0);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder storage = new StringBuilder(" " + storages.get(0).getClass().getTypeName() + "{ contains");
        for (Storage storage1 : storages
             ) {
            storage.append(" [ detail {" + storage1.getId() + "},");
        }
        if (storage.length() > 0) {storage.setLength(storage.length()-1);}
        storage.append(" ]");
        StringBuilder detail = new StringBuilder(" " + details.get(0).getClass().getTypeName() + "{ contains");
        for (Detail detail1 : details
        ) {
            detail.append(" [ detail {" + detail1.getId() + "},");
        }
        if (detail.length() > 0) {detail.setLength(detail.length()-1);}
        detail.append(" ]");


        return this.getClass().getSimpleName() + " {" +
                "id=" + id +
                ", companyName='" + companyName +
                "' , taxNumber='" + taxNumber +
                "' , isGovernmentAgency=" + isGovernmentAgency +
                " , userId=" + userId +
                " , storages=" + storage +
                " , details="+ detail + "}";
    }
}
