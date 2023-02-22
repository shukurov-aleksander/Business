package com.ku.business.controller;

import com.ku.business.dto.DocumentDto;
import com.ku.business.dto.DocumentListDto;
import com.ku.business.dto.DocumentSaveDto;
import com.ku.business.service.impl.DocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/documents")
public class DocumentController {
    @Autowired
    private DocumentServiceImpl service;

    @GetMapping("{id}")
    public Optional<DocumentDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Set<DocumentListDto> findAll() {
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
