package com.ku.business.dtomapper;

import com.ku.business.dto.ServiceDto;
import com.ku.business.dto.ServiceListDto;
import com.ku.business.dto.ServiceSaveOrUpdateDto;
import com.ku.business.entity.Service;

import java.util.ArrayList;
import java.util.List;

public class ServiceDtoMapper implements Mapper<Service, ServiceDto, ServiceListDto, ServiceSaveOrUpdateDto> {

    @Override
    public ServiceDto toDto(Service service) {
        return new ServiceDto(
                service.getId(),
                service.getServiceName(),
                service.getSum(),
                service.getServiceDescription()
        );
    }

    @Override
    public ServiceListDto toListDto(Service service) {
        return new ServiceListDto(
                service.getId(),
                service.getServiceName()
        );
    }

    @Override
    public List<ServiceListDto> toDtoList(List<Service> services) {
        List<ServiceListDto> serviceListDtos = new ArrayList<>();
        for (Service service : services) {
            serviceListDtos.add(toListDto(service));
        }
        return serviceListDtos;
    }

    @Override
    public ServiceSaveOrUpdateDto toSaveOrUpdateDto(Service service) {
        return new ServiceSaveOrUpdateDto(
                service.getId(),
                service.getServiceName(),
                service.getSum()
        );
    }

    @Override
    public Service fromSaveOrUpdateDto(ServiceSaveOrUpdateDto saveOrUpdateDto) {
        return new Service(
                saveOrUpdateDto.getId(),
                saveOrUpdateDto.getServiceName(),
                saveOrUpdateDto.getSum(),
                null
        );
    }
}
