package com.ku.business.dtomapper;

import com.ku.business.dto.content.ContentDto;
import com.ku.business.dto.content.ContentListDto;
import com.ku.business.dto.content.ContentSaveOrUpdateDto;
import com.ku.business.entity.Content;

import java.util.ArrayList;
import java.util.List;

public class ContentDtoMapper {
    public Content fromDTOToEntity(ContentDto contentDTO) {
        return new Content(
                contentDTO.getId(),
                contentDTO.getQuantity(),
                new ServiceDtoMapper().fromDTOToEntity(contentDTO.getService()),
                new OrderDtoMapper().fromDTOListToEntityList(contentDTO.getOrders()));
    }

    public ContentDto fromEntityToDTO(Content content) {
        return new ContentDto(
                content.getId(),
                content.getQuantity(),
                new ServiceDtoMapper().fromEntityToDTO(content.getService()),
                new OrderDtoMapper().fromEntityListToDTOList(content.getOrders())
        );
    }
    public ContentListDto fromContentToContentListDTO(Content content) {
        return new ContentListDto(
                content.getId(),
                content.getQuantity()
        );
    }
    public Content fromContentListDTOtoContent(ContentListDto contentListDTO) {
        return new Content(
                contentListDTO.getId(),
                contentListDTO.getQuantity(),
                null,
                null
        );
    }
    public List<Content> fromDTOListToEntityList(List<ContentListDto> contentsDTO) {
        List<Content> contents = new ArrayList<>();
        for (ContentListDto contentListDTO : contentsDTO) {
            contents.add(fromContentListDTOtoContent(contentListDTO));
        }
        return contents;
    }

    public List<ContentListDto> fromEntityListToDTOList(List<Content> contents) {
        List<ContentListDto> contentsListDto = new ArrayList<>();
        for (Content content : contents) {
            contentsListDto.add(fromContentToContentListDTO(content));
        }
        return contentsListDto;
    }

    public ContentSaveOrUpdateDto fromEntityToSaveOrUpdateDTO(Content content) {
        return new ContentSaveOrUpdateDto(
                content.getId(),
                content.getQuantity(),
                new ServiceDtoMapper().fromEntityToDTO(content.getService())
        );
    }
}
