package com.ku.business.dto;

import com.ku.business.entity.OperationType;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DetailListDto {
    private Long id;
    private OperationType operationType;
}
