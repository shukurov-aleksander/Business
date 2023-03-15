package com.ku.business.service;

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

    public void save(String content) {
       // contentDao.save(ContentDtoMapper.fromSaveDto(content));
    }

    public void update(String content) {
     //   contentDao.save(ContentDtoMapper.fromSaveDto(content));
    }

    public void delete(Long id) {
     //   contentDao.deleteById(id);
    }
}
