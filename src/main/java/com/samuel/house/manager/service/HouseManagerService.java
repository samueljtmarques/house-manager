package com.samuel.house.manager.service;

import com.samuel.house.manager.message.HouseResponse;
import com.samuel.house.manager.message.StoreHouseRequest;

import java.util.List;

public interface HouseManagerService {
    Long saveHouse(StoreHouseRequest house);

    List<HouseResponse> getAllHouses();

    void deleteHouse(Long id);

    HouseResponse getHouse(Long id);
}
