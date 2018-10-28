package com.samuel.house.manager.controller;

import com.samuel.house.manager.message.HouseResponse;
import com.samuel.house.manager.message.StoreHouseRequest;
import com.samuel.house.manager.message.StoreHouseResponse;
import com.samuel.house.manager.message.error.ErrorMessageResponse;
import com.samuel.house.manager.service.HouseManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.samuel.house.manager.error.util.ControllerValidationUtil.parseLongWithException;
import static com.samuel.house.manager.model.Constants.HOUSE_NOT_FOUND;

@Slf4j
@RestController
@RequestMapping("/housemanager/houses/")
@Api("House Manager Controller")
@NoArgsConstructor
public class HouseManagerController {

    @Autowired
    private HouseManagerService houseManagerService;

    @ApiOperation(
            value = "Endpoint to retrieve a specific or all stored houses.",
            response = ResponseEntity.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            notes = "This endpoint allows you to retrieve one or all stored houses"
    )
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "The stored house(s)", response = HouseResponse.class)
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<HouseResponse> getHouses() {

        return houseManagerService.getAllHouses();
    }

    @ApiOperation(
            value = "Endpoint to retrieve a specific or all stored houses.",
            response = ResponseEntity.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            notes = "This endpoint allows you to retrieve one or all stored houses"
    )
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "The stored house(s)", response = HouseResponse.class),
                    @ApiResponse(code = 404, message = HOUSE_NOT_FOUND, response = ErrorMessageResponse.class)

            }
    )
    @GetMapping(value = "{houseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    HouseResponse getHouse(@PathVariable String houseId) {

        return houseManagerService.getHouse(parseLongWithException("houseId", houseId));
    }

    @ApiOperation(
            value = "Endpoint to store a new house",
            response = ResponseEntity.class,
            produces = MediaType.TEXT_PLAIN_VALUE,
            notes = "This endpoint allows you to store a new house"
    )
    @ApiResponses(
            {
                    @ApiResponse(code = 200, message = "Save a new house", response = StoreHouseResponse.class),
                    @ApiResponse(code = 400, message = "Bad Request - house request fields not correct", response = ErrorMessageResponse.class)
            }
    )
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    StoreHouseResponse saveHouse(@Validated @RequestBody StoreHouseRequest storeHouseRequest) {
        return StoreHouseResponse.builder()
                .houseId(houseManagerService.saveHouse(storeHouseRequest))
                .build();
    }

    @ApiOperation(
            value = "Endpoint to delete a stored house",
            notes = "This endpoint allows you to delete a stored house"
    )
    @ApiResponses(
            {
                    @ApiResponse(code = 204, message = "Device unsubscribed"),
                    @ApiResponse(code = 404, message = HOUSE_NOT_FOUND, response = ErrorMessageResponse.class)
            }
    )
    @RequestMapping(value = "{houseId}", method = {RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHouse(@PathVariable String houseId) {
        houseManagerService.deleteHouse(parseLongWithException("houseId", houseId));
    }
}
