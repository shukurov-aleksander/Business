package com.ku.business.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StorageListDto {
    private Long id;
    private Integer quantity;
}
