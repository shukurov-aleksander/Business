package com.ku.business.controller;

import com.ku.business.dto.CompanyDto;
import com.ku.business.dto.CompanyListDto;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Companies", description = "Companies information")
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    @GetMapping
    @Operation(summary = "Find companies")
    public List<CompanyListDto> findAll(
            @Parameter(description = "Company name", example = "Company name №709 .inc")
            @RequestParam(value = "companyName", required = false) String companyName,
            @Parameter(description = "Tax number", example = "0000000000000709")
            @RequestParam(value = "taxNumber", required = false)  String taxNumber,
            @Parameter(description = "User id", example = "4")
            @RequestParam(value = "userId", required = false)  Long userId,
            @Parameter(description = "is government agency", example = "false")
            @RequestParam(value = "isGovernmentAgency", required = false)  Boolean isGovernmentAgency,
            @RequestParam(value = "companyStatus", required = false)
            @Parameter(description = "Company status", example = "REGISTERED") CompanyStatus companyStatus,
            @Parameter(description = "Offset", example = "0")
            @RequestParam(defaultValue = "0") Integer offset,
            @Parameter(description = "Limit", example = "20")
            @RequestParam(defaultValue = "20") Integer limit
    ) {
        return companyService.findAll(
                new CompanyFilter()
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
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(example = "Company was not found"))),
            @ApiResponse(responseCode = "501", description = "Internal server error")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find company by id")
    public CompanyDto findById(@Parameter(description = "id", example = "1") @PathVariable Long id) {
        return companyService.findById(id);
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
}
