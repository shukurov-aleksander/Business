package com.ku.business.service;

import com.ku.business.dto.ContentDto;
import com.ku.business.dto.ContentListDto;
import com.ku.business.dto.ContentSaveDto;
import com.ku.business.dtomapper.ContentDtoMapper;
import com.ku.business.repository.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {
    private final ContentRepository repository;

    public ContentService(ContentRepository repository) {
        this.repository = repository;
    }

    public Optional<ContentDto> findById(Long id) {
        return Optional.of(ContentDtoMapper.toDto(repository.findById(id).get()));
    }

    public List<ContentListDto> findAll() {
        return ContentDtoMapper.toListDto(repository.findAll());
    }

    public void save(ContentSaveDto content) {
        repository.save(ContentDtoMapper.fromSaveDto(content));
    }

    public void update(ContentSaveDto content) {
        repository.save(ContentDtoMapper.fromSaveDto(content));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
