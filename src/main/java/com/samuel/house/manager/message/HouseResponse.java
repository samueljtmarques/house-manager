package com.samuel.house.manager.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@ApiModel
@Getter
@Builder
@EqualsAndHashCode
public class HouseResponse {
    @ApiModelProperty(required = true, notes = "Id of the new House")
    private Long id;
    @ApiModelProperty(notes = "Street and number where the house belongs",
            example = "Maximilliamstrasse 29")
    private String streetAddress;
    @ApiModelProperty(notes = "The house's city",
            example = "Munich")
    private String city;
    @ApiModelProperty(notes = "The house's postcode",
            example = "80331")
    private String postcode;
    @ApiModelProperty(notes = "The house's country",
            example = "Germany")
    private String country;
}
