package com.ku.business.service;

import com.ku.business.dto.ServiceDto;
import com.ku.business.dto.ServiceListDto;
import com.ku.business.dto.ServiceSaveDto;
import com.ku.business.dtomapper.ServiceDtoMapper;
import com.ku.business.repository.ServiceRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {
    private final ServiceRepository repository;

    public ServiceService(ServiceRepository repository) {
        this.repository = repository;
    }

    public Optional<ServiceDto> findById(Long id) {
        return Optional.of(ServiceDtoMapper.toDto(repository.findById(id).get()));
    }

    public List<ServiceListDto> findAll() {
        return ServiceDtoMapper.toListDto(repository.findAll());
    }

    public void save(ServiceSaveDto service) {
        repository.save(ServiceDtoMapper.fromSaveDto(service));
    }

    public void update(ServiceSaveDto service) {
        repository.save(ServiceDtoMapper.fromSaveDto(service));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
