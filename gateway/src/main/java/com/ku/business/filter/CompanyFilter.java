package com.ku.business.filter;

import com.ku.business.entity.CompanyStatus;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CompanyFilter{
    private String taxNumber;
    private String companyName;
    private Boolean isGovernmentAgency;
    private Long userId;
    private Integer limit;
    private Integer offset;
    private String sortBy;
    private CompanyStatus companyStatus;
}
