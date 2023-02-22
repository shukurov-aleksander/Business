package com.ku.business.controller;

import com.ku.business.dto.DocumentDto;
import com.ku.business.dto.DocumentListDto;
import com.ku.business.dto.DocumentSaveDto;
import com.ku.business.service.DocumentService;
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
@RequestMapping("/documents")
public class DocumentController {
    private DocumentService service;
    @Autowired
    public void setService(DocumentService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Optional<DocumentDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<DocumentListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(DocumentSaveDto document) {
        service.save(document);
    }

    @PutMapping
    public void update(DocumentSaveDto document) {
        service.update(document);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
