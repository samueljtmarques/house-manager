package com.samuel.house.manager.converter;

import com.samuel.house.manager.message.StoreHouseRequest;
import com.samuel.house.manager.model.entity.House;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StoreHouseRequestConverter implements Converter<StoreHouseRequest, House> {

    @Override
    public House convert(StoreHouseRequest storeHouseRequest) {
        House house = new House();
        house.setStreetAddress(storeHouseRequest.getStreet() + ' ' + storeHouseRequest.getNumber());
        house.setCity(storeHouseRequest.getCity());
        house.setCountry(storeHouseRequest.getCountry());
        house.setPostcode(storeHouseRequest.getPostcode());
        return house;
    }
}
