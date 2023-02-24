package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Schema(description = "Company data transfer object")
@Accessors(chain = true)
public class CompanyDto {
    @Schema(description = "Id", required = true, example = "1")
    private Long id;
    @Schema(description = "Company name", required = true, example = "Company name")
    private String companyName;
    @Schema(description = "Company tax number", required = true, example = "0000000000000001")
    private String taxNumber;
    @Schema(description = "Does company government agency?", required = true, example = "true")
    private Boolean isGovernmentAgency;
    @Schema(description = "Company user", required = true, example = "1")
    private Long userId;
    @Schema(description = "Company storages", required = true)
    private Set<StorageListDto> storages;
    @Schema(description = "Company details", required = true)
    private Set<DetailListDto> details;
}

