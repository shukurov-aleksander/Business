package com.ku.business.controller;

import com.ku.business.entity.Detail;
import com.ku.business.service.impl.DetailServiceImpl;
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
@RequestMapping(value = "/details")
public class DetailController {
    @Autowired
    private DetailServiceImpl service;

    @GetMapping("{id}")
    public Optional<Detail> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Detail> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save(Detail detail) {
        service.save(detail);
    }

    @PutMapping
    public void update(Detail detail) {
        service.update(detail);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
