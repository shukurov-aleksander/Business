package com.ku.business.dtomapper;

import com.ku.business.dto.ServiceDto;
import com.ku.business.dto.ServiceListDto;
import com.ku.business.dto.ServiceSaveOrUpdateDto;
import com.ku.business.entity.Service;

import java.util.HashSet;
import java.util.Set;

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

    public static Set<ServiceListDto> toDtoList(Set<Service> services) {
        Set<ServiceListDto> serviceListDtos = new HashSet<>();
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
        return new Service()
                .setId(saveOrUpdateDto.getId())
                .setServiceName(saveOrUpdateDto.getServiceName())
                .setSum(saveOrUpdateDto.getSum()
        );
    }
}
