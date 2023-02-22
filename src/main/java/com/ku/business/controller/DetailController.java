package com.ku.business.controller;

import com.ku.business.dto.DetailDto;
import com.ku.business.dto.DetailListDto;
import com.ku.business.dto.DetailSaveDto;
import com.ku.business.service.impl.DetailServiceImpl;
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
@RequestMapping(value = "/details")
public class DetailController {
    @Autowired
    private DetailServiceImpl service;

    @GetMapping("{id}")
    public Optional<DetailDto> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public Set<DetailListDto> findAll() {
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
