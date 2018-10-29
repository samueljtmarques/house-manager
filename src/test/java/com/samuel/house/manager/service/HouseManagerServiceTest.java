package com.samuel.house.manager.service;

import com.samuel.house.manager.exception.HouseNotFoundException;
import com.samuel.house.manager.message.HouseResponse;
import com.samuel.house.manager.message.StoreHouseRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class HouseManagerServiceTest {

    @Autowired
    private HouseManagerService underTest;

    @org.junit.jupiter.api.Test
    public void storeHousesSuccefuly() {
        HouseResponse house = buildNewHouseRequest("Munich",
                "Maximillianstrasse 29", "Germany", "33441");

        HouseResponse house2 = buildNewHouseRequest("Frankfurt",
                "Abendstrasse 13", "Germany", "56561");

        List<HouseResponse> houses = underTest.getAllHouses();

        Assertions.assertAll("Assert that store operation went right",
                () -> Assertions.assertEquals(2,houses.size()),
                () -> Assertions.assertEquals(houses.get(0).getCity(), house.getCity()),
                () -> Assertions.assertEquals(houses.get(0).getCountry(), house.getCountry()),
                () -> Assertions.assertEquals(houses.get(0).getStreetAddress(), house.getStreetAddress()),
                () -> Assertions.assertEquals(houses.get(1).getCity(), house2.getCity()),
                () -> Assertions.assertEquals(houses.get(1).getCountry(), house2.getCountry()),
                () -> Assertions.assertEquals(houses.get(1).getStreetAddress(), house2.getStreetAddress()));
    }

    @org.junit.jupiter.api.Test
    public void deleteAllHousesSuccessfully() {
        buildNewHouseRequest("Berlin",
                "NachMitagstrasse 10", "Germany", "12345");

        buildNewHouseRequest("Hamburg",
                "Morgenstrasse 9", "Germany", "99999");

        underTest.getAllHouses().stream()
                .forEach(houseToDelete -> underTest.deleteHouse(houseToDelete.getId()));

        Assertions.assertTrue(underTest.getAllHouses().isEmpty());
    }

    @org.junit.jupiter.api.Test
    public void deleteHousesSuccessfully() {
        HouseResponse houseResponse = buildNewHouseRequest("Munich",
                "Maximillianstrasse 12", "Germany","12345");

        HouseResponse houseResponse2 = buildNewHouseRequest("Frankfurt",
                "Abendstrasse 23", "Germany", "80331");

        underTest.deleteHouse(houseResponse.getId());

        List<HouseResponse> allHouses = underTest.getAllHouses();
            Assertions.assertAll("Assert that the house list doesn't contain houseResponse",
                () -> Assertions.assertTrue(allHouses.contains(houseResponse2)),
                () -> Assertions.assertFalse(allHouses.contains(houseResponse)));
    }

    private HouseResponse buildNewHouseRequest(String city,
                                               String street,
                                               String country,
                                               String postCode) {
        StoreHouseRequest house = new StoreHouseRequest();
        house.setCity(city);
        house.setStreet(street);
        house.setCountry(country);
        house.setPostcode(postCode);
        Long id = underTest.saveHouse(house);
        return underTest.getHouse(id);
    }

    @org.junit.jupiter.api.Test
    public void tryToDeleteNonExistingHouse() {

        Assertions.assertThrows(HouseNotFoundException.class,
                () -> underTest.deleteHouse(9999l));
    }

    @org.junit.jupiter.api.Test
    public void tryToReadNonExistingHouse() {
        Assertions.assertThrows(HouseNotFoundException.class,
                () -> underTest.getHouse(9999l));
    }
}