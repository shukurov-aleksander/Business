package com.ku.business.controller;

import com.ku.business.entity.Content;
import com.ku.business.service.impl.ContentServiceImpl;
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
@RequestMapping(value = "/contents")
public class ContentController {
    @Autowired
    private ContentServiceImpl service;

    @GetMapping("/{id}")
    public Optional<Content> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Content> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(Content content) {
        service.save(content);
    }

    @PutMapping
    public void update(Content content) {
        service.update(content);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
