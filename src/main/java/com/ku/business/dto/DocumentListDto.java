package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Document list data transfer object for list of the companies")
@Accessors(chain = true)
public class DocumentListDto {
    @Schema(description = "Uniq identification of the document data transfer object")
    private Long id;
    @Schema(description = "Document content of the document data transfer object")
    private String documentContent;
}
