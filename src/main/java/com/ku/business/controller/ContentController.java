package com.ku.business.controller;

import com.ku.business.dto.ContentDto;
import com.ku.business.dto.ContentListDto;
import com.ku.business.dto.ContentSaveDto;
import com.ku.business.service.impl.ContentServiceImpl;
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
@RequestMapping(value = "/contents")
public class ContentController {
    @Autowired
    private ContentServiceImpl service;

    @GetMapping("/{id}")
    public Optional<ContentDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Set<ContentListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(ContentSaveDto content) {
        service.save(content);
    }

    @PutMapping
    public void update(ContentSaveDto content) {
        service.update(content);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
