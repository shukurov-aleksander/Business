package com.ku.business.dtomapper;

import com.ku.business.dto.DetailDto;
import com.ku.business.dto.DetailListDto;
import com.ku.business.dto.DetailSaveDto;
import com.ku.business.entity.Detail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DetailDtoMapper {
    public static DetailDto toDto(Detail detail) {
        return new DetailDto()
                .setId(detail.getId())
                .setCompany(CompanyDtoMapper.toDto(detail.getCompany()))
                .setOrder(OrderDtoMapper.toDto(detail.getOrder()))
                .setOperationType(detail.getOperationType());
    }

    public static DetailListDto toListDto(Detail detail) {
        return new DetailListDto()
                .setId(detail.getId())
                .setOperationType(detail.getOperationType());
    }

    public static List<DetailListDto> toListDto(List<Detail> details) {
        List<DetailListDto> detailsListDTO = new ArrayList<>();
        for (Detail detail : details) {
            detailsListDTO.add(toListDto(detail));
        }
        return detailsListDTO;
    }

    public static Set<DetailListDto> toListDto(Set<Detail> details) {
        Set<DetailListDto> detailsListDTO = new HashSet<>();
        for (Detail detail : details) {
            detailsListDTO.add(toListDto(detail));
        }
        return detailsListDTO;
    }

    public static DetailSaveDto toSaveDto(Detail detail) {
        return new DetailSaveDto()
                .setId(detail.getId())
                .setOperationType(detail.getOperationType());
    }

    public static Detail fromSaveDto(DetailSaveDto detailSaveDto) {
        return new Detail()
                .setId(detailSaveDto.getId())
                .setOperationType(detailSaveDto.getOperationType());
    }
}