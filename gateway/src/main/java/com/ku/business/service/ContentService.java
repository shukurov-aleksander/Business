package com.ku.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ContentService {
    private RestTemplate restTemplate;

    public String findById(Long id) {
        String findContentByIdUrl = "http://localhost:8080/contents/" + id;
        return restTemplate.getForObject(findContentByIdUrl, String.class);
    }

    public String findAll() {
        String findAllContentsUrl = "http://localhost:8080/contents/";
        return restTemplate.getForObject(findAllContentsUrl, String.class);
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

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
