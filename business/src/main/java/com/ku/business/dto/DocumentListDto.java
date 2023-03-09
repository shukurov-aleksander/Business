package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Document list data transfer object for list of the companies")
@Accessors(chain = true)
public class DocumentListDto {
    @Schema(description = "Id", required = true, example = "1")
    private Long id;
    @Schema(description = "Document content", required = true, example = "Some content")
    private String documentContent;
}
