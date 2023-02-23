package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Schema(description = "Company data transfer object")
@Accessors(chain = true)
public class CompanyDto {
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
    @Schema(description = "Actual storages of the company data transfer object")
    private Set<StorageListDto> storages;
    @Schema(description = "Details of the company data transfer object")
    private Set<DetailListDto> details;
}

