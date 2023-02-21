package com.ku.business.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ContentSaveOrUpdateDto {
    private Long id;
    private Long quantity;
    private ServiceDto service;
}
