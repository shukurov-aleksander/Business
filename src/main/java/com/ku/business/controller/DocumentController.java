package com.ku.business.controller;

import com.ku.business.entity.Document;
import com.ku.business.service.impl.DocumentServiceImpl;
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
@RequestMapping(value = "/documents")
public class DocumentController {
    @Autowired
    private DocumentServiceImpl service;

    @GetMapping("{id}")
    public Optional<Document> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Document> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(Document document) {
        service.save(document);
    }

    @PutMapping
    public void update(Document document) {
        service.update(document);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
