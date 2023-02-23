package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Document save or update data transfer object")
@Accessors(chain = true)
public class DocumentSaveDto {
    @Schema(description = "Uniq identification of the document data transfer object")
    private Long id;
    @Schema(description = "Document content of the document data transfer object")
    private String documentContent;
}
