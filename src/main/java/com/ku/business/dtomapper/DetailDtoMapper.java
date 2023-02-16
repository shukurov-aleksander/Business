package com.ku.business.dtomapper;

import com.ku.business.dto.detail.DetailDto;
import com.ku.business.dto.detail.DetailListDto;
import com.ku.business.dto.detail.DetailSaveOrUpdateDto;
import com.ku.business.entity.Detail;

import java.util.ArrayList;
import java.util.List;

public class DetailDtoMapper {
    public Detail fromDTOToEntity(DetailDto dTO) {
        return new Detail(
                dTO.getId(),
                new CompanyDtoMapper().fromDTOToEntity(dTO.getCompany()),
                new OrderDtoMapper().fromDTOToEntity(dTO.getOrder()),
                dTO.getOperationType()
        );
    }

    public DetailDto fromEntityToDTO(Detail detail) {
        return new DetailDto(
                detail.getId(),
                 new CompanyDtoMapper().fromEntityToDTO(detail.getCompany()),
                 new OrderDtoMapper().fromEntityToDTO(detail.getOrder()),
                detail.getOperationType()
        );
    }
    public DetailListDto fromDetailToDetailListDTO(Detail detail) {
        return new DetailListDto(
                detail.getId(),
                detail.getOperationType()
        );
    }
    public Detail fromDetailListDTOtoDetail(DetailListDto detailListDTO) {
        return new Detail(
                detailListDTO.getId(),
                null,
                null,
                detailListDTO.getOperationType()
        );
    }

    public List<Detail> fromDTOListToEntityList(List<DetailListDto> dTOList) {
        List<Detail> details = new ArrayList<>();
        for (DetailListDto detailListDTO : dTOList) {
            details.add(fromDetailListDTOtoDetail(detailListDTO));
        }
        return details;
    }

    public List<DetailListDto> fromEntityListToDTOList(List<Detail> details) {
        List<DetailListDto> detailsListDTO = new ArrayList<>();
        for (Detail detail : details) {
            detailsListDTO.add(fromDetailToDetailListDTO(detail));
        }
        return detailsListDTO;
    }

    public DetailSaveOrUpdateDto fromEntityToSaveOrUpdateDTO(Detail detail) {
        return new DetailSaveOrUpdateDto(
                detail.getId(),
                detail.getOrder(),
                detail.getOperationType()
        );
    }
}
