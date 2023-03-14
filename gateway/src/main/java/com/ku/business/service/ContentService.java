package com.ku.business.service;

import com.ku.business.dto.ContentSaveDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ContentService {

    public String findById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String fooUrl = "http://localhost:8080/contents/" + id;
        return restTemplate.getForObject(fooUrl, String.class);
    }

    public String findAll() {
        RestTemplate restTemplate = new RestTemplate();
        String fooUrl = "http://localhost:8080/contents/";
        return restTemplate.getForObject(fooUrl, String.class);
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
