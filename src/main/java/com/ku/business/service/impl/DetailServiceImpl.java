package com.ku.business.service.impl;

import com.ku.business.dto.DetailDto;
import com.ku.business.dto.DetailListDto;
import com.ku.business.dto.DetailSaveDto;
import com.ku.business.dtomapper.DetailDtoMapper;
import com.ku.business.repository.DetailRepository;
import com.ku.business.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class DetailServiceImpl implements CrudService<DetailDto, DetailListDto, DetailSaveDto> {
    private final DetailRepository repository;

    public DetailServiceImpl(DetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<DetailDto> findById(Long id) {
        return Optional.of(DetailDtoMapper.toDto(repository.findById(id).get()));
    }

    @Override
    public Set<DetailListDto> findAll() {
        return DetailDtoMapper.toListDto(repository.findAll());
    }

    @Override
    public void save(DetailSaveDto detail) {
        repository.save(DetailDtoMapper.fromSaveDto(detail));
    }

    @Override
    public void update(DetailSaveDto detail) {
        repository.save(DetailDtoMapper.fromSaveDto(detail));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
