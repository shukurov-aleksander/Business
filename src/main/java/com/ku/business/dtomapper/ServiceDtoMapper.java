package com.ku.business.dtomapper;

import com.ku.business.dto.ServiceDto;
import com.ku.business.dto.ServiceListDto;
import com.ku.business.dto.ServiceSaveOrUpdateDto;
import com.ku.business.entity.Service;

import java.util.ArrayList;
import java.util.List;

public class ServiceDtoMapper {
    public static ServiceDto toDto(Service service) {
        return new ServiceDto(
                service.getId(),
                service.getServiceName(),
                service.getSum(),
                service.getServiceDescription()
        );
    }

    public static ServiceListDto toListDto(Service service) {
        return new ServiceListDto(
                service.getId(),
                service.getServiceName()
        );
    }

    public static List<ServiceListDto> toDtoList(List<Service> services) {
        List<ServiceListDto> serviceListDtos = new ArrayList<>();
        for (Service service : services) {
            serviceListDtos.add(toListDto(service));
        }
        return serviceListDtos;
    }

    public static ServiceSaveOrUpdateDto toSaveOrUpdateDto(Service service) {
        return new ServiceSaveOrUpdateDto(
                service.getId(),
                service.getServiceName(),
                service.getSum()
        );
    }

    public static Service fromSaveOrUpdateDto(ServiceSaveOrUpdateDto saveOrUpdateDto) {
        return new Service(
                saveOrUpdateDto.getId(),
                saveOrUpdateDto.getServiceName(),
                saveOrUpdateDto.getSum(),
                null
        );
    }
}
