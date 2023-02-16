package com.ku.business.dtomapper;

import com.ku.business.dto.detail.DetailDTO;
import com.ku.business.dto.detail.DetailListDTO;
import com.ku.business.dto.detail.DetailSaveOrUpdateDTO;
import com.ku.business.entity.Detail;

import java.util.ArrayList;
import java.util.List;

public class DetailDTOMapper {
    public Detail fromDTOToEntity(DetailDTO dTO) {
        return new Detail(
                dTO.getId(),
                new CompanyDTOMapper().fromDTOToEntity(dTO.getCompany()),
                new OrderDTOMapper().fromDTOToEntity(dTO.getOrder()),
                dTO.getOperationType()
        );
    }

    public DetailDTO fromEntityToDTO(Detail detail) {
        return new DetailDTO(
                detail.getId(),
                 new CompanyDTOMapper().fromEntityToDTO(detail.getCompany()),
                 new OrderDTOMapper().fromEntityToDTO(detail.getOrder()),
                detail.getOperationType()
        );
    }
    public DetailListDTO fromDetailToDetailListDTO(Detail detail) {
        return new DetailListDTO(
                detail.getId(),
                detail.getOperationType()
        );
    }
    public Detail fromDetailListDTOtoDetail(DetailListDTO detailListDTO) {
        return new Detail(
                detailListDTO.getId(),
                null,
                null,
                detailListDTO.getOperationType()
        );
    }

    public List<Detail> fromDTOListToEntityList(List<DetailListDTO> dTOList) {
        List<Detail> details = new ArrayList<>();
        for (DetailListDTO detailListDTO : dTOList) {
            details.add(fromDetailListDTOtoDetail(detailListDTO));
        }
        return details;
    }

    public List<DetailListDTO> fromEntityListToDTOList(List<Detail> details) {
        List<DetailListDTO> detailsListDTO = new ArrayList<>();
        for (Detail detail : details) {
            detailsListDTO.add(fromDetailToDetailListDTO(detail));
        }
        return detailsListDTO;
    }

    public DetailSaveOrUpdateDTO fromEntityToSaveOrUpdateDTO(Detail detail) {
        return new DetailSaveOrUpdateDTO(
                detail.getId(),
                detail.getOrder(),
                detail.getOperationType()
        );
    }
}
