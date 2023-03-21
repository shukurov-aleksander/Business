package com.ku.business.service;

import com.ku.business.dto.DetailDto;
import com.ku.business.dto.DetailListDto;
import com.ku.business.dto.DetailSaveDto;
import com.ku.business.dtomapper.DetailDtoMapper;
import com.ku.business.repository.DetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailService {
    private DetailDao detailRepository;

    public Optional<DetailDto> findById(Long id) {
        return Optional.of(DetailDtoMapper.toDto(detailRepository.findById(id).get()));
    }

    public List<DetailListDto> findAll() {
        return DetailDtoMapper.toListDto(detailRepository.findAll());
    }

    public void save(DetailSaveDto detail) {
        detailRepository.save(DetailDtoMapper.fromSaveDto(detail));
    }

    public void update(DetailSaveDto detail) {
        detailRepository.save(DetailDtoMapper.fromSaveDto(detail));
    }

    public void delete(Long id) {
        detailRepository.deleteById(id);
    }

    @Autowired
    public void setDetailRepository(DetailDao detailRepository) {
        this.detailRepository = detailRepository;
    }
}