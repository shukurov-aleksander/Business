package com.ku.business.service;

import com.ku.business.dto.ContentDto;
import com.ku.business.dto.ContentListDto;
import com.ku.business.dto.ContentSaveDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    public Optional<ContentDto> findById(Long id) {
       // return Optional.of(ContentDtoMapper.toDto(contentDao.findById(id).get()));
        return null;
    }

    public List<ContentListDto> findAll() {
       // return ContentDtoMapper.toListDto(contentDao.findAll());
        return null;
    }

    public void save(ContentSaveDto content) {
       // contentDao.save(ContentDtoMapper.fromSaveDto(content));
    }

    public void update(ContentSaveDto content) {
     //   contentDao.save(ContentDtoMapper.fromSaveDto(content));
    }

    public void delete(Long id) {
     //   contentDao.deleteById(id);
    }
}
