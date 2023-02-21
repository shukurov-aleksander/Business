package com.ku.business.dtomapper;

import com.ku.business.dto.ContentDto;
import com.ku.business.dto.ContentListDto;
import com.ku.business.dto.ContentSaveOrUpdateDto;
import com.ku.business.entity.Content;

import java.util.HashSet;
import java.util.Set;

public class ContentDtoMapper {
    public static ContentDto toDto(Content content) {
        return new ContentDto()
                .setId(content.getId())
                .setQuantity(content.getQuantity())
                .setService(new ServiceDtoMapper().toDto(content.getService()))
                .setOrders(new OrderDtoMapper().toListDto(content.getOrders())
        );
    }

    public static ContentListDto toListDto(Content content) {
        return new ContentListDto()
                .setId(content.getId())
                .setQuantity(content.getQuantity()
        );
    }

    public static Set<ContentListDto> toListDto(Set<Content> contents) {
        Set<ContentListDto> contentsListDto = new HashSet<>();
        for (Content content : contents) {
            contentsListDto.add(toListDto(content));
        }
        return contentsListDto;
    }

    public static ContentSaveOrUpdateDto toSaveOrUpdateDto(Content content) {
        return new ContentSaveOrUpdateDto()
                .setId(content.getId())
                .setQuantity(content.getQuantity())
                .setService(new ServiceDtoMapper().toDto(content.getService())
        );
    }

    public static Content fromSaveOrUpdateDto(ContentSaveOrUpdateDto contentSaveOrUpdateDto) {
        return new Content()
                .setId(contentSaveOrUpdateDto.getId())
                .setQuantity(contentSaveOrUpdateDto.getQuantity()
        );
    }
}
