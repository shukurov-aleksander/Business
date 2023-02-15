package com.ku.business.dtomapper;

import com.ku.business.dto.storage.StorageDTO;
import com.ku.business.dto.storage.StorageListDTO;
import com.ku.business.entity.Storage;

import java.util.List;

public class StorageDTOMapper {

    public Storage fromDTOToEntity(StorageDTO storageDTO) {
        return new Storage(
                storageDTO.getId(),
                storageDTO.getQuantity(),
                new CompanyDTOMapper().fromDTOToEntity(storageDTO.getCompany()),
                new ServiceDTOMapper().fromDTOToEntity(storageDTO.getService())
        );
    }

    public StorageDTO fromEntityToDTO(Storage entity) {
        return new StorageDTO(
                entity.getId(),
                entity.getQuantity(),
                new CompanyDTOMapper().fromEntityToDTO(entity.getCompany()),
                new ServiceDTOMapper().fromEntityToDTO(entity.getService())
        );
    }

    public List<Storage> fromDTOListToEntityList(List<StorageListDTO> dTOList) {
        return null;
    }

    public List<StorageListDTO> fromEntityListToDTOList(List<Storage> entities) {
        return null;
    }

    public StorageDTO fromEntityToSaveOrUpdateDTO(Storage entity) {
        return null;
    }
}
