package com.ku.business.dtomapper;

import com.ku.business.dto.DetailDto;
import com.ku.business.dto.DetailListDto;
import com.ku.business.dto.DetailSaveOrUpdateDto;
import com.ku.business.entity.Detail;

import java.util.HashSet;
import java.util.Set;

public class DetailDtoMapper {
    public static DetailDto toDto(Detail detail) {
        return new DetailDto(
                detail.getId(),
                 new CompanyDtoMapper().toDto(detail.getCompany()),
                 new OrderDtoMapper().toDto(detail.getOrder()),
                detail.getOperationType()
        );
    }

    public static DetailListDto toListDto(Detail detail) {
        return new DetailListDto(
                detail.getId(),
                detail.getOperationType()
        );
    }

    public static Set<DetailListDto> toDtoList(Set<Detail> details) {
        Set<DetailListDto> detailsListDTO = new HashSet<>();
        for (Detail detail : details) {
            detailsListDTO.add(toListDto(detail));
        }
        return detailsListDTO;
    }

    public static DetailSaveOrUpdateDto toSaveOrUpdateDto(Detail detail) {
        return new DetailSaveOrUpdateDto(
                detail.getId(),
                detail.getOrder(),
                detail.getOperationType()
        );
    }

    public static Detail fromSaveOrUpdateDto(DetailSaveOrUpdateDto detailSaveOrUpdateDto) {
        return new Detail(
                detailSaveOrUpdateDto.getId(),
                null,
                detailSaveOrUpdateDto.getOrder(),
                detailSaveOrUpdateDto.getOperationType()
        );
    }
}
