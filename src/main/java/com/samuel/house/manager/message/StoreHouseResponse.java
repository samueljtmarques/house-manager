package com.samuel.house.manager.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter
@Setter
@Builder
public class StoreHouseResponse {

    @ApiModelProperty(required = true, notes = "Id of the new House")
    private Long houseId;
}
