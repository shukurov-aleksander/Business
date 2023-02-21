package com.ku.business.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ServiceSaveOrUpdateDto {
    private Long id;
    private String serviceName;
    private Long sum;
}
