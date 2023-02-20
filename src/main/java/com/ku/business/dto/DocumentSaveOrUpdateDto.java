package com.ku.business.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DocumentSaveOrUpdateDto {
    private Long id;
    private String documentContent;
}
