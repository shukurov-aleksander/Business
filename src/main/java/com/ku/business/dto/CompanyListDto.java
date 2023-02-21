package com.ku.business.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CompanyListDto {
    private Long id;
    private String companyName;
    private String taxNumber;
    private Boolean isGovernmentAgency;
}
