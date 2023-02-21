package com.ku.business.dtomapper;

import com.ku.business.dto.DetailDto;
import com.ku.business.dto.DetailListDto;
import com.ku.business.dto.DetailSaveOrUpdateDto;
import com.ku.business.entity.Detail;

import java.util.HashSet;
import java.util.Set;

public class DetailDtoMapper {
    public static DetailDto toDto(Detail detail) {
        return new DetailDto()
                .setId(detail.getId())
                .setCompany(new CompanyDtoMapper().toDto(detail.getCompany()))
                .setOrder(new OrderDtoMapper().toDto(detail.getOrder()))
                .setOperationType(detail.getOperationType()
        );
    }

    public static DetailListDto toListDto(Detail detail) {
        return new DetailListDto()
                .setId(detail.getId())
                .setOperationType(detail.getOperationType()
        );
    }

    public static Set<DetailListDto> toListDto(Set<Detail> details) {
        Set<DetailListDto> detailsListDTO = new HashSet<>();
        for (Detail detail : details) {
            detailsListDTO.add(toListDto(detail));
        }
        return detailsListDTO;
    }

    public static DetailSaveOrUpdateDto toSaveOrUpdateDto(Detail detail) {
        return new DetailSaveOrUpdateDto()
                .setId(detail.getId())
                .setOrder(detail.getOrder())
                .setOperationType(detail.getOperationType()
        );
    }

    public static Detail fromSaveOrUpdateDto(DetailSaveOrUpdateDto detailSaveOrUpdateDto) {
        return new Detail()
                .setId(detailSaveOrUpdateDto.getId())
                .setOrder(detailSaveOrUpdateDto.getOrder())
                .setOperationType(detailSaveOrUpdateDto.getOperationType()
        );
    }
}
