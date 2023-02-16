package com.ku.business.dtomapper;

import com.ku.business.dto.storage.StorageDto;
import com.ku.business.dto.storage.StorageListDto;
import com.ku.business.entity.Storage;

import java.util.List;

public class StorageDtoMapper {

    public Storage fromDTOToEntity(StorageDto storageDTO) {
        return new Storage(
                storageDTO.getId(),
                storageDTO.getQuantity(),
                new CompanyDtoMapper().fromDTOToEntity(storageDTO.getCompany()),
                new ServiceDtoMapper().fromDTOToEntity(storageDTO.getService())
        );
    }

    public StorageDto fromEntityToDTO(Storage entity) {
        return new StorageDto(
                entity.getId(),
                entity.getQuantity(),
                new CompanyDtoMapper().fromEntityToDTO(entity.getCompany()),
                new ServiceDtoMapper().fromEntityToDTO(entity.getService())
        );
    }

    public List<Storage> fromDTOListToEntityList(List<StorageListDto> dTOList) {
        return null;
    }

    public List<StorageListDto> fromEntityListToDTOList(List<Storage> entities) {
        return null;
    }

    public StorageDto fromEntityToSaveOrUpdateDTO(Storage entity) {
        return null;
    }
}
