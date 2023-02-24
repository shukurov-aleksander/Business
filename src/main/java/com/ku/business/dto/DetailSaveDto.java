package com.ku.business.dto;

import com.ku.business.entity.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Detail save or update data transfer object")
@Accessors(chain = true)
public class DetailSaveDto {
    @Schema(description = "Id", required = true, example = "1")
    private Long id;
    @Schema(description = "Detail operation type")
    private OperationType operationType;
}
