package com.ku.business.service;

import com.ku.business.dto.ContentDto;
import com.ku.business.dto.ContentListDto;
import com.ku.business.dto.ContentSaveDto;
import com.ku.business.dtomapper.ContentDtoMapper;
import com.ku.business.repository.ContentDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {
    private final ContentDao contentDao;

    public ContentService(ContentDao contentDao) {
        this.contentDao = contentDao;
    }

    public Optional<ContentDto> findById(Long id) {
        return Optional.of(ContentDtoMapper.toDto(contentDao.findById(id).get()));
    }

    public List<ContentListDto> findAll() {
        return ContentDtoMapper.toListDto(contentDao.findAll());
    }

    public void save(ContentSaveDto content) {
        contentDao.save(ContentDtoMapper.fromSaveDto(content));
    }

    public void update(ContentSaveDto content) {
        contentDao.save(ContentDtoMapper.fromSaveDto(content));
    }

    public void delete(Long id) {
        contentDao.deleteById(id);
    }
}
