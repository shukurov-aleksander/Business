package com.ku.business.controller;

import com.ku.business.dto.DetailDto;
import com.ku.business.dto.DetailListDto;
import com.ku.business.dto.DetailSaveDto;
import com.ku.business.service.DetailService;
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
@RequestMapping("/details")
public class DetailController {
    private DetailService service;
    @Autowired
    public void setService(DetailService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Optional<DetailDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<DetailListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(DetailSaveDto detail) {
        service.save(detail);
    }

    @PutMapping
    public void update(DetailSaveDto detail) {
        service.update(detail);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
