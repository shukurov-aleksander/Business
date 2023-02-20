package com.ku.business.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ContentListDto {
    private Long id;
    private Long quantity;
}
