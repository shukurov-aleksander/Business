package com.ku.business.service;

import com.ku.business.dto.ServiceDto;
import com.ku.business.dto.ServiceListDto;
import com.ku.business.dto.ServiceSaveDto;
import com.ku.business.dtomapper.ServiceDtoMapper;
import com.ku.business.exception.ServiceException;
import com.ku.business.repository.ServiceDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {
    private ServiceDao serviceDao;

    public Optional<ServiceDto> findById(Long id) {
        try {
            return Optional.of(ServiceDtoMapper.toDto(serviceDao.findById(id).get()));
        } catch (RuntimeException runtimeException) {
            throw ServiceException.notFoundException(
                    String.format("Can't find service with id=%d!", id),
                    runtimeException);
        }
    }

    public List<ServiceListDto> findAll() {
        return ServiceDtoMapper.toListDto(serviceDao.findAll());
    }

    public void save(ServiceSaveDto service) {
        serviceDao.save(ServiceDtoMapper.fromSaveDto(service));
    }

    public void update(ServiceSaveDto service) {
        serviceDao.save(ServiceDtoMapper.fromSaveDto(service));
    }

    public void delete(Long id) {
        serviceDao.deleteById(id);
    }

    @Autowired
    public void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }
}