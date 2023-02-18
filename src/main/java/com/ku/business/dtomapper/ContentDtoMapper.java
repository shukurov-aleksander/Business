package com.ku.business.dtomapper;

import com.ku.business.dto.ContentDto;
import com.ku.business.dto.ContentListDto;
import com.ku.business.dto.ContentSaveOrUpdateDto;
import com.ku.business.entity.Content;

import java.util.ArrayList;
import java.util.List;

public class ContentDtoMapper {
    public static ContentDto toDto(Content content) {
        return new ContentDto(
                content.getId(),
                content.getQuantity(),
                new ServiceDtoMapper().toDto(content.getService()),
                new OrderDtoMapper().toDtoList(content.getOrders())
        );
    }

    public static ContentListDto toListDto(Content content) {
        return new ContentListDto(
                content.getId(),
                content.getQuantity()
        );
    }

    public static List<ContentListDto> toDtoList(List<Content> contents) {
        List<ContentListDto> contentsListDto = new ArrayList<>();
        for (Content content : contents) {
            contentsListDto.add(toListDto(content));
        }
        return contentsListDto;
    }

    public static ContentSaveOrUpdateDto toSaveOrUpdateDto(Content content) {
        return new ContentSaveOrUpdateDto(
                content.getId(),
                content.getQuantity(),
                new ServiceDtoMapper().toDto(content.getService())
        );
    }

    public static Content fromSaveOrUpdateDto(ContentSaveOrUpdateDto contentSaveOrUpdateDto) {
        return new Content(
                contentSaveOrUpdateDto.getId(),
                contentSaveOrUpdateDto.getQuantity(),
                null,
                null
        );
    }
}
