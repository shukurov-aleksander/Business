package com.ku.business.dtomapper;

import com.ku.business.dto.StorageDto;
import com.ku.business.dto.StorageListDto;
import com.ku.business.dto.StorageSaveOrUpdateDto;
import com.ku.business.entity.Storage;

import java.util.ArrayList;
import java.util.List;

public class StorageDtoMapper implements Mapper<Storage, StorageDto, StorageListDto, StorageSaveOrUpdateDto> {

    @Override
    public StorageDto toDto(Storage storage) {
        return new StorageDto(
                storage.getId(),
                storage.getQuantity(),
                new CompanyDtoMapper().toDto(storage.getCompany()),
                new ServiceDtoMapper().toDto(storage.getService())
        );
    }

    @Override
    public StorageListDto toListDto(Storage storage) {
        return new StorageListDto(
                storage.getId(),
                storage.getQuantity()
        );
    }

    @Override
    public List<StorageListDto> toDtoList(List<Storage> storages) {
        List<StorageListDto> storageListDtos = new ArrayList<>();
        for (Storage storage : storages) {
            storageListDtos.add(toListDto(storage));
        }
        return storageListDtos;
    }

    @Override
    public StorageSaveOrUpdateDto toSaveOrUpdateDto(Storage storage) {
        return new StorageSaveOrUpdateDto(
                storage.getId(),
                storage.getQuantity()
        );
    }

    @Override
    public Storage fromSaveOrUpdateDto(StorageSaveOrUpdateDto saveOrUpdateDto) {
        return new Storage(
                saveOrUpdateDto.getId(),
                saveOrUpdateDto.getQuantity(),
                null,
                null
        );
    }
}
