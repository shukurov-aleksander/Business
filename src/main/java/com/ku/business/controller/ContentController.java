package com.ku.business.controller;

import com.ku.business.dto.ContentDto;
import com.ku.business.dto.ContentListDto;
import com.ku.business.dto.ContentSaveDto;
import com.ku.business.service.ContentService;
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
@RequestMapping("/contents")
public class ContentController {
    private ContentService service;
    @Autowired
    public void setService(ContentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Optional<ContentDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<ContentListDto> findAll() {
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
