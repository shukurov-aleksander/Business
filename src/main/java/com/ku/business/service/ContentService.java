package com.ku.business.service;

import com.ku.business.dto.ContentDto;
import com.ku.business.dto.ContentListDto;
import com.ku.business.dto.ContentSaveDto;
import com.ku.business.dtomapper.ContentDtoMapper;
import com.ku.business.exception.ServiceException;
import com.ku.business.repository.ContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {
    private ContentDao contentDao;

    public Optional<ContentDto> findById(Long id) {
        try {
           return Optional.of(ContentDtoMapper.toDto(contentDao.findById(id).get()));
        } catch (RuntimeException runtimeException) {
           throw ServiceException.notFoundException(
                   String.format("Can't find content with id=%d!", id),
                   runtimeException);
        }
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

    @Autowired
    public void setContentDao(ContentDao contentDao) {
        this.contentDao = contentDao;
    }
}