package com.ku.business.controller;

import com.ku.business.dto.DetailDto;
import com.ku.business.dto.DetailListDto;
import com.ku.business.dto.DetailSaveDto;
import com.ku.business.service.DetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@Tag(name = "Details", description = "Details information")
@RequestMapping("/details")
public class DetailController {
    private DetailService detailService;

    @GetMapping("{id}")
    @Operation(summary = "Find detail by id")
    public DetailDto findById(
        @Parameter(description = "Detail id", required = true, example = "1")
        @PathVariable Long id
    ) {
        return detailService.findById(id).get();
    }

    @GetMapping
    @Operation(summary = "Find details")
    public List<DetailListDto> findAll() {
        return detailService.findAll();
    }

    @PostMapping
    @Operation(summary = "Save detail")
    public void save(DetailSaveDto detail) {
        detailService.save(detail);
    }

    @PutMapping
    @Operation(summary = "Update detail")
    public void update(DetailSaveDto detail) {
        detailService.update(detail);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete detail by id")
    public void delete(
        @Parameter(description = "Detail id", required = true, example = "1")
        @PathVariable Long id
    ) {
        detailService.delete(id);
    }

    @Autowired
    public void setDetailService(DetailService detailService) {
        this.detailService = detailService;
    }
}
