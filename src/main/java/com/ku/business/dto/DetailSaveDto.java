package com.ku.business.dto;

import com.ku.business.entity.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Detail save or update data transfer object")
@Accessors(chain = true)
public class DetailSaveDto {
    @Schema(description = "Uniq identification of the detail data transfer object")
    private Long id;

    @Schema(description = "Type of operation")
    private OperationType operationType;
}
