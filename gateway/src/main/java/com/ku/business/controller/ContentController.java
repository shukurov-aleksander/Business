package com.ku.business.controller;


import com.ku.business.service.ContentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@Tag(name = "Contents", description = "Contents information")
@RequestMapping("/contents")
public class ContentController {
    private ContentService contentService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find content by id")
    public String findById(
        @Parameter(description = "Content id", required = true, example = "1")
        @PathVariable Long id
    ) {
        return contentService.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find contents")
    public String findAll() {
        return contentService.findAll();
    }

    @PostMapping
    @Operation(summary = "Save content")
    public void save(String content) {
        contentService.save(content);
    }

    @PutMapping
    @Operation(summary = "Update content")
    public void update(String content) {
        contentService.update(content);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete content by id")
    public void delete(
        @Parameter(description = "Content id", required = true, example = "1")
        @PathVariable Long id
    ) {
        contentService.delete(id);
    }

    @Autowired
    public void setContentService(ContentService contentService) {
        this.contentService = contentService;
    }
}
