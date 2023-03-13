package com.ku.business.controller;

import com.ku.business.dto.CompanyListDto;
import com.ku.business.entity.CompanyStatus;
import com.ku.business.filter.CompanyFilter;
import com.ku.business.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@Tag(name = "Companies", description = "Companies information")
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find companies")
    public String findAll(
            @Parameter(description = "Company name", example = "Company name №709 .inc")
            @RequestParam(value = "companyName", required = false) String companyName,
            @Parameter(description = "Tax number", example = "0000000000000709")
            @RequestParam(value = "taxNumber", required = false)  String taxNumber,
            @Parameter(description = "User id", example = "4")
            @RequestParam(value = "userId", required = false)  Long userId,
            @Parameter(description = "is government agency", example = "false")
            @RequestParam(value = "isGovernmentAgency", required = false)  Boolean isGovernmentAgency,
            @RequestParam(value = "companyStatus", required = false)
            @Schema(
                    type = "string", allowableValues = {"REGISTERED", "ACTIVE", "FREEZED", "ELIMINATED"},
                    description = "Company status",
                    example = "REGISTERED")
            CompanyStatus companyStatus,
            @Parameter(description = "Offset", example = "0")
            @RequestParam(defaultValue = "0") Integer offset,
            @Parameter(description = "Limit", example = "20")
            @RequestParam(defaultValue = "20") Integer limit
    ) {
        return companyService.findAll();  }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
}
