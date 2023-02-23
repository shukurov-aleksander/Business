package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Company list data transfer object for list of the companies")
@Accessors(chain = true)
public class CompanyListDto {
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Company name")
    private String companyName;
    @Schema(description = "Company tax number")
    private String taxNumber;
    @Schema(description = "Does company government agency?")
    private Boolean isGovernmentAgency;
}
