package com.ku.business.dtomapper;

import com.ku.business.dto.ServiceDto;
import com.ku.business.dto.ServiceListDto;
import com.ku.business.dto.ServiceSaveDto;
import com.ku.business.entity.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceDtoMapper {
    public static ServiceDto toDto(Service service) {
        return new ServiceDto()
                .setId(service.getId())
                .setServiceName(service.getServiceName())
                .setSum(service.getSum())
                .setServiceDescription(service.getServiceDescription());
    }

    public static ServiceListDto toListDto(Service service) {
        return new ServiceListDto()
                .setId(service.getId())
                .setServiceName(service.getServiceName());
    }

    public static Set<ServiceListDto> toListDto(Set<Service> services) {
        Set<ServiceListDto> serviceListDtos = new HashSet<>();
        for (Service service : services) {
            serviceListDtos.add(toListDto(service));
        }
        return serviceListDtos;
    }

    public static List<ServiceListDto> toListDto(List<Service> services) {
        List<ServiceListDto> serviceListDtos = new ArrayList<>();
        for (Service service : services) {
            serviceListDtos.add(toListDto(service));
        }
        return serviceListDtos;
    }

    public static ServiceSaveDto toSaveDto(Service service) {
        return new ServiceSaveDto()
                .setId(service.getId())
                .setServiceName(service.getServiceName())
                .setSum(service.getSum());
    }

    public static Service fromSaveDto(ServiceSaveDto saveOrUpdateDto) {
        return new Service()
                .setId(saveOrUpdateDto.getId())
                .setServiceName(saveOrUpdateDto.getServiceName())
                .setSum(saveOrUpdateDto.getSum());
    }
}
