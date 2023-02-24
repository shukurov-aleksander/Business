package com.ku.business.dtomapper;

import com.ku.business.dto.ContentDto;
import com.ku.business.dto.ContentListDto;
import com.ku.business.dto.ContentSaveDto;
import com.ku.business.entity.Content;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContentDtoMapper {
    public static ContentDto toDto(Content content) {
        return new ContentDto()
                .setId(content.getId())
                .setQuantity(content.getQuantity())
                .setService(ServiceDtoMapper.toDto(content.getService()))
                .setOrders(OrderDtoMapper.toListDto(content.getOrders()));
    }

    public static ContentListDto toListDto(Content content) {
        return new ContentListDto()
                .setId(content.getId())
                .setQuantity(content.getQuantity());
    }

    public static List<ContentListDto> toListDto(List<Content> contents) {
        List<ContentListDto> contentsListDto = new ArrayList<>();
        for (Content content : contents) {
            contentsListDto.add(toListDto(content));
        }
        return contentsListDto;
    }

    public static Set<ContentListDto> toListDto(Set<Content> contents) {
        Set<ContentListDto> contentsListDto = new HashSet<>();
        for (Content content : contents) {
            contentsListDto.add(toListDto(content));
        }
        return contentsListDto;
    }

    public static ContentSaveDto toSaveDto(Content content) {
        return new ContentSaveDto()
                .setId(content.getId())
                .setQuantity(content.getQuantity())
                .setService(new ServiceDtoMapper().toDto(content.getService())
        );
    }

    public static Content fromSaveDto(ContentSaveDto contentSaveDto) {
        return new Content()
                .setId(contentSaveDto.getId())
                .setQuantity(contentSaveDto.getQuantity());
    }
}
