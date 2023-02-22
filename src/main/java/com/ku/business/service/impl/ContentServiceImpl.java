package com.ku.business.service.impl;

import com.ku.business.dto.ContentDto;
import com.ku.business.dto.ContentListDto;
import com.ku.business.dto.ContentSaveDto;
import com.ku.business.dtomapper.ContentDtoMapper;
import com.ku.business.repository.ContentRepository;
import com.ku.business.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ContentServiceImpl implements CrudService<ContentDto, ContentListDto, ContentSaveDto> {
    private final ContentRepository repository;

    public ContentServiceImpl(ContentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ContentDto> findById(Long id) {
        return Optional.of(ContentDtoMapper.toDto(repository.findById(id).get()));
    }

    @Override
    public Set<ContentListDto> findAll() {
        return ContentDtoMapper.toListDto(repository.findAll());
    }

    @Override
    public void save(ContentSaveDto content) {
        repository.save(ContentDtoMapper.fromSaveDto(content));
    }

    @Override
    public void update(ContentSaveDto content) {
        repository.save(ContentDtoMapper.fromSaveDto(content));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
