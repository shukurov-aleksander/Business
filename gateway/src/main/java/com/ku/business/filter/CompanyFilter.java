package com.ku.business.filter;

import com.ku.business.entity.CompanyStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@Accessors(chain = true)
public class CompanyFilter{
    private Optional<String> taxNumber;
    private Optional<String> companyName;
    private Optional<Boolean> isGovernmentAgency;
    private Optional<Long> userId;
    private Optional<Integer> limit;
    private Optional<Integer> offset;
    private Optional<String> sortBy;
    private Optional<CompanyStatus> companyStatus;
}
