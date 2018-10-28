package com.samuel.house.manager.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for field validation errors
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class ValidationErrorFieldsDto {
    private List<FieldValidationErrorDto> fieldErrors = new ArrayList<>();

    @ApiModelProperty(notes = "List of field validation errors")
    public void addFieldError(String path, String message, String rejectedValue) {
        FieldValidationErrorDto error = new FieldValidationErrorDto(path, message, rejectedValue);
        fieldErrors.add(error);
    }
}
