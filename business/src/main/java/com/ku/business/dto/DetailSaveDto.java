package com.ku.business.dto;

import com.ku.business.entity.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Detail save or update data transfer object")
@Accessors(chain = true)
public class DetailSaveDto {
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Detail operation type", requiredMode = Schema.RequiredMode.REQUIRED, example = "PURCHASE")
    private OperationType operationType;
}