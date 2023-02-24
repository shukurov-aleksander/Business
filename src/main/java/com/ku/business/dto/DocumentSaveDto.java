package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Document save or update data transfer object")
@Accessors(chain = true)
public class DocumentSaveDto {
    @Schema(description = "Id", required = true, example = "1")
    private Long id;
    @Schema(description = "Document content", required = true, example = "Some content")
    private String documentContent;
}
