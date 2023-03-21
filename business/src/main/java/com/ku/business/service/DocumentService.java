package com.ku.business.service;

import com.ku.business.dto.DocumentDto;
import com.ku.business.dto.DocumentListDto;
import com.ku.business.dto.DocumentSaveDto;
import com.ku.business.dtomapper.DocumentDtoMapper;
import com.ku.business.repository.DocumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    private DocumentDao documentDao;

    public Optional<DocumentDto> findById(Long id) {
        return Optional.of(DocumentDtoMapper.toDto(documentDao.findById(id).get()));
    }

    public List<DocumentListDto> findAll() {
        return DocumentDtoMapper.toListDto(documentDao.findAll());
    }

    public void save(DocumentSaveDto document) {
        documentDao.save(DocumentDtoMapper.fromSaveDto(document));
    }

    public void update(DocumentSaveDto document) {
        documentDao.save(DocumentDtoMapper.fromSaveDto(document));
    }

    public void delete(Long id) {
        documentDao.deleteById(id);
    }

    @Autowired
    public void setDocumentDao(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }
}