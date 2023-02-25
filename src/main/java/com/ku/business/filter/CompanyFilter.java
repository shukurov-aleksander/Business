package com.ku.business.filter;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CompanyFilter{
    String taxNumber;
    String companyName;
    Boolean isGovernmentAgency;
    Long userId;
    Integer limit;
    Integer offset;
    String sortBy;
}
