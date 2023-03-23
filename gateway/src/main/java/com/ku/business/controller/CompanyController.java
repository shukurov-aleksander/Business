package com.ku.business.controller;

import com.ku.business.entity.CompanyStatus;
import com.ku.business.filter.CompanyFilter;
import com.ku.business.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Tag(name = "Companies", description = "Companies information")
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find companies")
    public String findAll(
            @Parameter(description = "Company name", example = "Company name №709 .inc")
            @RequestParam(value = "companyName", required = false) Optional<String> companyName,
            @Parameter(description = "Tax number", example = "0000000000000709")
            @RequestParam(value = "taxNumber", required = false)  Optional<String> taxNumber,
            @Parameter(description = "User id", example = "4")
            @RequestParam(value = "userId", required = false)  Optional<Long> userId,
            @Parameter(description = "is government agency", example = "false")
            @RequestParam(value = "isGovernmentAgency", required = false)  Optional<Boolean> isGovernmentAgency,
            @RequestParam(value = "companyStatus", required = false)
            @Parameter(description = "Company status", example = "REGISTERED") Optional<CompanyStatus> companyStatus,
            @Parameter(description = "Offset", example = "0")
            @RequestParam(defaultValue = "0") Optional<Integer> offset,
            @Parameter(description = "Limit", example = "20")
            @RequestParam(defaultValue = "20") Optional<Integer> limit
    ) {
        return companyService.findAll(new CompanyFilter()
                .setCompanyName(companyName)
                .setTaxNumber(taxNumber)
                .setUserId(userId)
                .setIsGovernmentAgency(isGovernmentAgency)
                .setCompanyStatus(companyStatus)
                .setLimit(limit)
                .setOffset(offset));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(example = "Some message 1")))
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find company by id")
    public String findById(@Parameter(description = "id", example = "1") @PathVariable Long id) {
        return companyService.findById(id);
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
}
