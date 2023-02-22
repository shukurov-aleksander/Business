package com.ku.business.service.impl;

import com.ku.business.dto.ServiceDto;
import com.ku.business.dto.ServiceListDto;
import com.ku.business.dto.ServiceSaveDto;
import com.ku.business.dtomapper.ServiceDtoMapper;
import com.ku.business.repository.ServiceRepository;
import com.ku.business.service.CrudService;

import java.util.Set;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements CrudService<ServiceDto, ServiceListDto, ServiceSaveDto> {
    private final ServiceRepository repository;

    public ServiceServiceImpl(ServiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ServiceDto> findById(Long id) {
        return Optional.of(ServiceDtoMapper.toDto(repository.findById(id).get()));
    }

    @Override
    public Set<ServiceListDto> findAll() {
        return ServiceDtoMapper.toListDto(repository.findAll());
    }

    @Override
    public void save(ServiceSaveDto service) {
        repository.save(ServiceDtoMapper.fromSaveDto(service));
    }

    @Override
    public void update(ServiceSaveDto service) {
        repository.save(ServiceDtoMapper.fromSaveDto(service));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
