package com.samuel.house.manager.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class FieldValidationErrorDto {
    @ApiModelProperty(notes = "Field that failed validation")
    private String field;

    @ApiModelProperty(notes = "Failure message")
    private String message;

    @ApiModelProperty(notes = "Value for the filed that was rejected")
    private String rejectedValue;
}
