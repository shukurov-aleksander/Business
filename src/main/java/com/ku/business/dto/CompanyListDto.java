package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Company list data transfer object for list of the companies")
@Accessors(chain = true)
public class CompanyListDto {
    @Schema(description = "Uniq identification of the company data transfer object")
    private Long id;
    @Schema(description = "Name of the company data transfer object")
    private String companyName;
    @Schema(description = "Tax number of the company data transfer object")
    private String taxNumber;
    @Schema(description = "Does company data transfer object government agency?")
    private Boolean isGovernmentAgency;
}
