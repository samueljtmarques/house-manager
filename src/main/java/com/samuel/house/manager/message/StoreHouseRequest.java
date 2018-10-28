package com.samuel.house.manager.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ApiModel
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreHouseRequest {
    @NotBlank
    @Pattern(regexp = "^[A-Za-z ]*$")
    @ApiModelProperty(notes = "Street where the house belongs",
            example = "Maximilliamstrasse")
    private String street;
    @NotBlank
    @Pattern(regexp = "^[0-9]*$")
    @Length(max = 3)
    @ApiModelProperty(notes = "The Street's number where the house belongs",
            example = "12")
    private String number;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z ]*$")
    @ApiModelProperty(notes = "The house's city",
            example = "Munich")
    private String city;
    @NotBlank
    @Pattern(regexp = "^[0-9]*$")
    @ApiModelProperty(notes = "The house's postcode",
            example = "80331")
    private String postcode;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z ]*$")
    @ApiModelProperty(notes = "The house's country",
            example = "Germany")
    private String country;
}
