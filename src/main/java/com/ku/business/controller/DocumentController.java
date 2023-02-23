package com.ku.business.controller;

import com.ku.business.dto.DocumentDto;
import com.ku.business.dto.DocumentListDto;
import com.ku.business.dto.DocumentSaveDto;
import com.ku.business.service.DocumentService;
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
@Tag(name = "Documents", description = "Table of documents")
@RequestMapping("/documents")
public class DocumentController {
    private DocumentService service;
    @Autowired
    public void setService(DocumentService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    @Operation(summary = "Information about document by ID")
    public Optional<DocumentDto> findById(
            @Parameter(description = "Uniq identification of the document")
            @PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    @Operation(summary = "Get all documents from table")
    public List<DocumentListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Save document to database")
    public void save(DocumentSaveDto document) {
        service.save(document);
    }

    @PutMapping
    @Operation(summary = "Update existing document in database")
    public void update(DocumentSaveDto document) {
        service.update(document);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete document from table by id")
    public void delete(
            @Parameter(description = "Uniq identification of the document")
            @PathVariable Long id) {
        service.delete(id);
    }
}
