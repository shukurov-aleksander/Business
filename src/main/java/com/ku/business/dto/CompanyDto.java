package com.ku.business.dto;

import java.util.List;
import java.util.Set;

public class CompanyDto {
    private Long id;
    private String companyName;
    private String taxNumber;
    private Boolean isGovernmentAgency;
    private Long userId;
    private List<StorageDto> storages;
    private Set<DetailDto> details;
}
