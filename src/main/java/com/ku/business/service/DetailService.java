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

import static com.ku.business.exception.ServiceException.notFoundException;

@Service
public class DetailService {
    private DetailDao detailRepository;

    public Optional<DetailDto> findById(Long id) {
        try {
            return Optional.of(DetailDtoMapper.toDto(detailRepository.findById(id).get()));
        } catch (RuntimeException runtimeException) {
            throw notFoundException(String.format("Can't find detail with id=%d!", id), runtimeException);
        }
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