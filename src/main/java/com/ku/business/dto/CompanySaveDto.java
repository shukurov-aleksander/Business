package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Company save or update data transfer object")
@Accessors(chain = true)
public class CompanySaveDto {
    @Schema(description = "Uniq identification of the company data transfer object")
    private Long id;
    @Schema(description = "Name of the company data transfer object")
    private String companyName;
    @Schema(description = "Tax number of the company data transfer object")
    private String taxNumber;
    @Schema(description = "Does company data transfer object government agency?")
    private Boolean isGovernmentAgency;
    @Schema(description = "User of the company data transfer object")
    private Long userId;
}
