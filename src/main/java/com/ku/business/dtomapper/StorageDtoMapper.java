package com.ku.business.dtomapper;

import com.ku.business.dto.StorageDto;
import com.ku.business.dto.StorageListDto;
import com.ku.business.dto.StorageSaveOrUpdateDto;
import com.ku.business.entity.Storage;

import java.util.HashSet;
import java.util.Set;

public class StorageDtoMapper {
    public static StorageDto toDto(Storage storage) {
        return new StorageDto(
                storage.getId(),
                storage.getQuantity(),
                new CompanyDtoMapper().toDto(storage.getCompany()),
                new ServiceDtoMapper().toDto(storage.getService())
        );
    }

    public static StorageListDto toListDto(Storage storage) {
        return new StorageListDto(
                storage.getId(),
                storage.getQuantity()
        );
    }

    public static Set<StorageListDto> toDtoList(Set<Storage> storages) {
        Set<StorageListDto> storageListDtos = new HashSet<>();
        for (Storage storage : storages) {
            storageListDtos.add(toListDto(storage));
        }
        return storageListDtos;
    }

    public static StorageSaveOrUpdateDto toSaveOrUpdateDto(Storage storage) {
        return new StorageSaveOrUpdateDto(
                storage.getId(),
                storage.getQuantity()
        );
    }

    public static Storage fromSaveOrUpdateDto(StorageSaveOrUpdateDto saveOrUpdateDto) {
        return new Storage()
                .setId(saveOrUpdateDto.getId())
                .setQuantity(saveOrUpdateDto.getQuantity()
        );
    }
}
