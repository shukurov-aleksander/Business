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
import java.util.Optional;

@RestController
@Tag(name = "Contents", description = "Table of contents")
@RequestMapping("/contents")
public class ContentController {
    private ContentService service;
    @Autowired
    public void setService(ContentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Information about content by ID")
    public Optional<ContentDto> findById(
            @Parameter(description = "Uniq identification of the content")
            @PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    @Operation(summary = "Get all contents from table")
    public List<ContentListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Save content to database")
    public void save(ContentSaveDto content) {
        service.save(content);
    }

    @PutMapping
    @Operation(summary = "Update existing content in database")
    public void update(ContentSaveDto content) {
        service.update(content);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete content from table by id")
    public void delete(
            @Parameter(description = "Uniq identification of the content")
            @PathVariable Long id) {
        service.delete(id);
    }
}
