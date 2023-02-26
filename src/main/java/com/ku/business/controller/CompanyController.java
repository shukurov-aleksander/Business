package com.ku.business.controller;

import com.ku.business.dto.CompanyListDto;
import com.ku.business.entity.CompanyStatus;
import com.ku.business.filter.CompanyFilter;
import com.ku.business.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam(value = "company_name", required = false) String companyName,
            @Parameter(description = "Tax number", example = "0000000000000709")
            @RequestParam(value = "tax_number", required = false)  String taxNumber,
            @Parameter(description = "User id", example = "4")
            @RequestParam(value = "user_id", required = false)  Long userId,
            @Parameter(description = "is government agency", example = "false")
            @RequestParam(value = "is_government_agency", required = false)  Boolean isGovernmentAgency,
            @Parameter(description = "Company status", example = "REGISTERED")
            @RequestParam(defaultValue = "REGISTERED") CompanyStatus companyStatus,
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

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
}
