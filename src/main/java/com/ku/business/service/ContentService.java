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
    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Optional<ContentDto> findById(Long id) {
        return Optional.of(ContentDtoMapper.toDto(contentRepository.findById(id).get()));
    }

    public List<ContentListDto> findAll() {
        return ContentDtoMapper.toListDto(contentRepository.findAll());
    }

    public void save(ContentSaveDto content) {
        contentRepository.save(ContentDtoMapper.fromSaveDto(content));
    }

    public void update(ContentSaveDto content) {
        contentRepository.save(ContentDtoMapper.fromSaveDto(content));
    }

    public void delete(Long id) {
        contentRepository.deleteById(id);
    }
}
