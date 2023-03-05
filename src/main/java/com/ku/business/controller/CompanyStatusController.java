package com.ku.business.controller;

import com.ku.business.entity.CompanyStatus;
import com.ku.business.service.CompanyStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Company status", description = "Companies status information")
@RequestMapping("/companyStatusHistories")
public class CompanyStatusController {
    private CompanyStatusService companyStatusService;

    @PutMapping
    @Operation(summary = "Update company status")
    public void update(
            @Parameter(description = "Company id", example = "1")
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "companyStatus", required = false)
            @Parameter(description = "Company status",example = "REGISTERED") CompanyStatus companyStatus
    ) {
        companyStatusService.save(id, companyStatus);
    }

    @Autowired
    public void setCompanyService(CompanyStatusService companyStatusService) {
        this.companyStatusService = companyStatusService;
    }
}