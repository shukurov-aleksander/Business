package com.ku.business.controller;

import com.ku.business.dto.ContentDto;
import com.ku.business.dto.ContentListDto;
import com.ku.business.dto.ContentSaveDto;
import com.ku.business.service.ContentService;
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
@Tag(name = "Contents", description = "Contents information")
@RequestMapping("/contents")
public class ContentController {
    private ContentService service;

    @GetMapping("/{id}")
    @Operation(summary = "Find content by id")
    public ContentDto findById(
        @Parameter(description = "Content id", required = true, example = "1")
        @PathVariable Long id
    ) {
        return service.findById(id).get();
    }

    @GetMapping
    @Operation(summary = "Find contents")
    public List<ContentListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Save content")
    public void save(ContentSaveDto content) {
        service.save(content);
    }

    @PutMapping
    @Operation(summary = "Update content")
    public void update(ContentSaveDto content) {
        service.update(content);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete content by id")
    public void delete(
        @Parameter(description = "Content id", required = true, example = "1")
        @PathVariable Long id
    ) {
        service.delete(id);
    }

    @Autowired
    public void setService(ContentService service) {
        this.service = service;
    }
}
