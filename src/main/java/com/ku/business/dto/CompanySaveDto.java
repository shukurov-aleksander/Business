package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Company save or update data transfer object")
@Accessors(chain = true)
public class CompanySaveDto {
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Company name", requiredMode = Schema.RequiredMode.REQUIRED, example = "Company name")
    private String companyName;
    @Schema(description = "Company tax number", requiredMode = Schema.RequiredMode.REQUIRED, example = "0000000000000001")
    private String taxNumber;
    @Schema(description = "Is government agency", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    private Boolean isGovernmentAgency;
    @Schema(description = "Company user", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long userId;
}