package com.ku.business.service.impl;

import com.ku.business.dto.StorageDto;
import com.ku.business.dto.StorageListDto;
import com.ku.business.dto.StorageSaveDto;
import com.ku.business.dtomapper.StorageDtoMapper;
import com.ku.business.repository.StorageRepository;
import com.ku.business.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.Optional;

@Service
public class StorageServiceImpl implements CrudService<StorageDto, StorageListDto, StorageSaveDto> {
    private final StorageRepository repository;

    public StorageServiceImpl(StorageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<StorageDto> findById(Long id) {
        return Optional.of(StorageDtoMapper.toDto(repository.findById(id).get()));
    }

    @Override
    public Set<StorageListDto> findAll() {
        return StorageDtoMapper.toListDto(repository.findAll());
    }

    @Override
    public void save(StorageSaveDto storage) {
        repository.save(StorageDtoMapper.fromSaveDto(storage));
    }

    @Override
    public void update(StorageSaveDto storage) {
        repository.save(StorageDtoMapper.fromSaveDto(storage));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
