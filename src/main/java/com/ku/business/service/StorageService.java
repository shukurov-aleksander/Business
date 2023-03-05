package com.ku.business.service;

import com.ku.business.dto.StorageDto;
import com.ku.business.dto.StorageListDto;
import com.ku.business.dto.StorageSaveDto;
import com.ku.business.dtomapper.StorageDtoMapper;
import com.ku.business.exception.ServiceException;
import com.ku.business.repository.StorageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageService {
    private StorageDao storageDao;

    public Optional<StorageDto> findById(Long id) {
        try {
            return Optional.of(StorageDtoMapper.toDto(storageDao.findById(id).get()));
        } catch (RuntimeException runtimeException) {
            throw ServiceException.notFoundException(
                    String.format("Can't find storage with id=%d!", id),
                    runtimeException);
        }
    }

    public List<StorageListDto> findAll() {
        return StorageDtoMapper.toListDto(storageDao.findAll());
    }

    public void save(StorageSaveDto storage) {
        storageDao.save(StorageDtoMapper.fromSaveDto(storage));
    }

    public void update(StorageSaveDto storage) {
        storageDao.save(StorageDtoMapper.fromSaveDto(storage));
    }

    public void delete(Long id) {
        storageDao.deleteById(id);
    }

    @Autowired
    public void setStorageDao(StorageDao storageDao) {
        this.storageDao = storageDao;
    }
}