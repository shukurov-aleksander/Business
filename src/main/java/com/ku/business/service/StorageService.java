package com.ku.business.service;

import com.ku.business.dto.StorageDto;
import com.ku.business.dto.StorageListDto;
import com.ku.business.dto.StorageSaveDto;
import com.ku.business.dtomapper.StorageDtoMapper;
import com.ku.business.repository.StorageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageService {
    private final StorageRepository repository;

    public StorageService(StorageRepository repository) {
        this.repository = repository;
    }

    public Optional<StorageDto> findById(Long id) {
        return Optional.of(StorageDtoMapper.toDto(repository.findById(id).get()));
    }

    public List<StorageListDto> findAll() {
        return StorageDtoMapper.toListDto(repository.findAll());
    }

    public void save(StorageSaveDto storage) {
        repository.save(StorageDtoMapper.fromSaveDto(storage));
    }

    public void update(StorageSaveDto storage) {
        repository.save(StorageDtoMapper.fromSaveDto(storage));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
