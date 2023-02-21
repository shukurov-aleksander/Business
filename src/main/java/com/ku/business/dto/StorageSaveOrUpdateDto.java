package com.ku.business.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StorageSaveOrUpdateDto {
    private Long id;
    private Integer quantity;
}
