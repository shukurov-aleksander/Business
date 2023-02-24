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
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Optional<ServiceDto> findById(Long id) {
        return Optional.of(ServiceDtoMapper.toDto(serviceRepository.findById(id).get()));
    }

    public List<ServiceListDto> findAll() {
        return ServiceDtoMapper.toListDto(serviceRepository.findAll());
    }

    public void save(ServiceSaveDto service) {
        serviceRepository.save(ServiceDtoMapper.fromSaveDto(service));
    }

    public void update(ServiceSaveDto service) {
        serviceRepository.save(ServiceDtoMapper.fromSaveDto(service));
    }

    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }
}
