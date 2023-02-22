package com.ku.business.dtomapper;

import com.ku.business.dto.StorageDto;
import com.ku.business.dto.StorageListDto;
import com.ku.business.dto.StorageSaveDto;
import com.ku.business.entity.Storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StorageDtoMapper {
    public static StorageDto toDto(Storage storage) {
        return new StorageDto()
                .setId(storage.getId())
                .setQuantity(storage.getQuantity())
                .setCompany(CompanyDtoMapper.toDto(storage.getCompany()))
                .setService(ServiceDtoMapper.toDto(storage.getService()));
    }

    public static StorageListDto toListDto(Storage storage) {
        return new StorageListDto()
                .setId(storage.getId())
                .setQuantity(storage.getQuantity());
    }

    public static Set<StorageListDto> toListDto(Set<Storage> storages) {
        Set<StorageListDto> storageListDtos = new HashSet<>();
        for (Storage storage : storages) {
            storageListDtos.add(toListDto(storage));
        }
        return storageListDtos;
    }

    public static List<StorageListDto> toListDto(List<Storage> storages) {
        List<StorageListDto> storageListDtos = new ArrayList<>();
        for (Storage storage : storages) {
            storageListDtos.add(toListDto(storage));
        }
        return storageListDtos;
    }

    public static StorageSaveDto toSaveDto(Storage storage) {
        return new StorageSaveDto()
                .setId(storage.getId())
                .setQuantity(storage.getQuantity());
    }

    public static Storage fromSaveDto(StorageSaveDto saveOrUpdateDto) {
        return new Storage()
                .setId(saveOrUpdateDto.getId())
                .setQuantity(saveOrUpdateDto.getQuantity());
    }
}
