package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Company list data transfer object for list of the companies")
@Accessors(chain = true)
public class CompanyListDto {
    @Schema(description = "Id", required = true, example = "1")
    private Long id;
    @Schema(description = "Company name", required = true, example = "Company name")
    private String companyName;
    @Schema(description = "Company tax number", required = true, example = "0000000000000001")
    private String taxNumber;
    @Schema(description = "Does company government agency?", required = true, example = "true")
    private Boolean isGovernmentAgency;
}
