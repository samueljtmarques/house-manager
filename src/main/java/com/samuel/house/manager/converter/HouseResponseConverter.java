package com.samuel.house.manager.converter;

import com.samuel.house.manager.message.HouseResponse;
import com.samuel.house.manager.model.entity.House;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HouseResponseConverter implements Converter<House, HouseResponse> {

    @Override
    public HouseResponse convert(House house) {
        return HouseResponse.builder()
                .id(house.getId())
                .streetAddress(house.getStreetAddress())
                .city(house.getCity())
                .country(house.getCountry())
                .postcode(house.getPostcode())
                .build();
    }
}
