package com.ku.business.dtomapper;

import com.ku.business.dto.content.ContentDTO;
import com.ku.business.dto.content.ContentListDTO;
import com.ku.business.dto.content.ContentSaveOrUpdateDTO;
import com.ku.business.entity.Content;

import java.util.ArrayList;
import java.util.List;

public class ContentDTOMapper {
    public Content fromDTOToEntity(ContentDTO contentDTO) {
        return new Content(
                contentDTO.getId(),
                contentDTO.getQuantity(),
                new ServiceDTOMapper().fromDTOToEntity(contentDTO.getService()),
                new OrderDTOMapper().fromDTOListToEntityList(contentDTO.getOrders()));
    }

    public ContentDTO fromEntityToDTO(Content content) {
        return new ContentDTO(
                content.getId(),
                content.getQuantity(),
                new ServiceDTOMapper().fromEntityToDTO(content.getService()),
                new OrderDTOMapper().fromEntityListToDTOList(content.getOrders())
        );
    }
    public ContentListDTO fromContentToContentListDTO(Content content) {
        return new ContentListDTO(
                content.getId(),
                content.getQuantity()
        );
    }
    public Content fromContentListDTOtoContent(ContentListDTO contentListDTO) {
        return new Content(
                contentListDTO.getId(),
                contentListDTO.getQuantity(),
                null,
                null
        );
    }
    public List<Content> fromDTOListToEntityList(List<ContentListDTO> contentsDTO) {
        List<Content> contents = new ArrayList<>();
        for (ContentListDTO contentListDTO : contentsDTO) {
            contents.add(fromContentListDTOtoContent(contentListDTO));
        }
        return contents;
    }

    public List<ContentListDTO> fromEntityListToDTOList(List<Content> contents) {
        List<ContentListDTO> contentsListDto = new ArrayList<>();
        for (Content content : contents) {
            contentsListDto.add(fromContentToContentListDTO(content));
        }
        return contentsListDto;
    }

    public ContentSaveOrUpdateDTO fromEntityToSaveOrUpdateDTO(Content content) {
        return new ContentSaveOrUpdateDTO(
                content.getId(),
                content.getQuantity(),
                new ServiceDTOMapper().fromEntityToDTO(content.getService())
        );
    }
}
