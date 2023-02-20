package com.ku.business.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StorageDto {
    private Long id;
    private Integer quantity;
    private CompanyDto company;
    private ServiceDto service;
}
