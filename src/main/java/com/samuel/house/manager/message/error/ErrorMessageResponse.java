package com.samuel.house.manager.message.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.samuel.house.manager.model.dto.ValidationErrorFieldsDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageResponse {
    @ApiModelProperty(required = true, notes = "The URL of the request.")
    private String url;

    @ApiModelProperty(required = true, notes = "The exception message that was thrown.")
    private String reason;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(notes = "Validation errors.")
    private ValidationErrorFieldsDto validationErrors;
}
