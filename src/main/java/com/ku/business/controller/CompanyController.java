package com.ku.business.controller;

import com.ku.business.dto.CompanyDto;
import com.ku.business.dto.CompanyListDto;
import com.ku.business.dto.CompanySaveDto;
import com.ku.business.filter.CompanyFilter;
import com.ku.business.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@Tag(name = "Companies", description = "Companies information")
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    @GetMapping("/{id}")
    @Operation(summary = "Find company by id")
    public CompanyDto findById(
        @Parameter(description = "Company id", required = true, example = "1")
        @PathVariable("id") Long id
    ) {
        return companyService.findById(id).get();
    }

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
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return companyService.findAll(
                new CompanyFilter()
                        .setCompanyName(companyName)
                        .setTaxNumber(taxNumber)
                        .setUserId(userId)
                        .setIsGovernmentAgency(isGovernmentAgency)
                        .setLimit(limit)
                        .setOffset(offset)
                        .setSortBy(sortBy)
                );
    }

    @PostMapping
    @Operation(summary = "Save company")
    public void save(CompanySaveDto company) {
        companyService.save(company);
    }

    @PutMapping
    @Operation(summary = "Update company")
    public void update(CompanySaveDto company) {
        companyService.update(company);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete company by id")
    public void delete(
        @Parameter(description = "Company id", required = true, example = "1")
        @PathVariable Long id
    ) {
        companyService.delete(id);
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
}
