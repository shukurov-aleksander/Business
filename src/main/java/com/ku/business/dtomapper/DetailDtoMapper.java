package com.ku.business.dtomapper;

import com.ku.business.dto.DetailDto;
import com.ku.business.dto.DetailListDto;
import com.ku.business.dto.DetailSaveOrUpdateDto;
import com.ku.business.entity.Detail;

import java.util.ArrayList;
import java.util.List;

public class DetailDtoMapper implements Mapper<Detail, DetailDto, DetailListDto, DetailSaveOrUpdateDto> {
    @Override
    public DetailDto toDto(Detail detail) {
        return new DetailDto(
                detail.getId(),
                 new CompanyDtoMapper().toDto(detail.getCompany()),
                 new OrderDtoMapper().toDto(detail.getOrder()),
                detail.getOperationType()
        );
    }

    @Override
    public DetailListDto toListDto(Detail detail) {
        return new DetailListDto(
                detail.getId(),
                detail.getOperationType()
        );
    }

    @Override
    public List<DetailListDto> toDtoList(List<Detail> details) {
        List<DetailListDto> detailsListDTO = new ArrayList<>();
        for (Detail detail : details) {
            detailsListDTO.add(toListDto(detail));
        }
        return detailsListDTO;
    }

    @Override
    public DetailSaveOrUpdateDto toSaveOrUpdateDto(Detail detail) {
        return new DetailSaveOrUpdateDto(
                detail.getId(),
                detail.getOrder(),
                detail.getOperationType()
        );
    }

    @Override
    public Detail fromSaveOrUpdateDto(DetailSaveOrUpdateDto detailSaveOrUpdateDto) {
        return new Detail(
                detailSaveOrUpdateDto.getId(),
                null,
                detailSaveOrUpdateDto.getOrder(),
                detailSaveOrUpdateDto.getOperationType()
        );
    }
}
