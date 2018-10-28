package com.samuel.house.manager.converter;

import com.samuel.house.manager.message.HouseResponse;
import com.samuel.house.manager.message.StoreHouseRequest;
import com.samuel.house.manager.model.entity.House;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ConvertersTest {

    @Autowired
    private Converter<StoreHouseRequest, House> storeHouseRequestHouseConverter;

    @Autowired
    private Converter<House, HouseResponse> houseResponseConverter;

    private static String street;
    private static String number;
    private static String city;
    private static String postcode;
    private static String country;
    private static House house;

    @BeforeAll
    public static void init() {
        street = "Maximilliamstrasse";
        number = "21";
        city = "Munich";
        postcode = "80221";
        country = "Germany";
        house = new House();
        house.setStreetAddress(street + ' ' + number);
        house.setCity(city);
        house.setPostcode(postcode);
        house.setCountry(country);
    }

    @Test
    public void testConvertStoreHouseRequestToHouse() {
        StoreHouseRequest storeHouseRequest
                = new StoreHouseRequest(street,
                number, city, postcode, country);
        Assertions.assertEquals(house, storeHouseRequestHouseConverter.convert(storeHouseRequest));
    }

    @Test
    public void convertHouseResponseConverter() {
        HouseResponse houseResponse = HouseResponse.builder()
                .streetAddress(street + ' ' + number)
                .city(city)
                .country(country)
                .postcode(postcode)
                .build();

        Assertions.assertEquals(houseResponse, houseResponseConverter.convert(house));
    }
}