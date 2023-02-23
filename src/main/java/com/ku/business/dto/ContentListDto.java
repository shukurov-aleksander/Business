package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;

@Data
@Schema(description = "Content list data transfer object for the list of the contents")
@Accessors(chain = true)
public class ContentListDto {
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Content quantity")
    @Min(1)
    private Long quantity;
}
