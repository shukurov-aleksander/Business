package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Schema(description = "Company data transfer object")
@Accessors(chain = true)
public class CompanyDto {
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Company name")
    private String companyName;
    @Schema(description = "Company tax number")
    private String taxNumber;
    @Schema(description = "Does company government agency?")
    private Boolean isGovernmentAgency;
    @Schema(description = "Company user")
    private Long userId;
    @Schema(description = "Company storages", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<StorageListDto> storages;
    @Schema(description = "Company details", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<DetailListDto> details;
}

