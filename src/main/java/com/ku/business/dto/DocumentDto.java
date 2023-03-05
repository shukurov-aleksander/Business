package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Document data transfer object")
@Accessors(chain = true)
public class DocumentDto {
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Document order", requiredMode = Schema.RequiredMode.REQUIRED)
    private OrderDto order;
    @Schema(description = "Document content", requiredMode = Schema.RequiredMode.REQUIRED, example = "Some content")
    private String documentContent;
}