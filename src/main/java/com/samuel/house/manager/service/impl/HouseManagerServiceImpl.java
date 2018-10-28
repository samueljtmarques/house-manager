package com.samuel.house.manager.service.impl;

import com.samuel.house.manager.exception.HouseNotFoundException;
import com.samuel.house.manager.message.HouseResponse;
import com.samuel.house.manager.message.StoreHouseRequest;
import com.samuel.house.manager.model.entity.House;
import com.samuel.house.manager.repository.HouseRepository;
import com.samuel.house.manager.service.HouseManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HouseManagerServiceImpl implements HouseManagerService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private Converter<StoreHouseRequest, House> houseRequestToHouseConverter;

    @Autowired
    private Converter<House, HouseResponse> houseResponseConverter;

    @Override
    @Transactional
    public Long saveHouse(StoreHouseRequest storeHouseRequest) {
        log.debug("Attempt to store a house with street '{}' - city '{}' - country '{}'",
                storeHouseRequest.getStreet(), storeHouseRequest.getCity(), storeHouseRequest.getCountry());
        House houseToStore = houseRequestToHouseConverter.convert(storeHouseRequest);
        House storedHouse = houseRepository.save(houseToStore);
        log.info("The house with id #" + storedHouse.getId() + " was successfully stored.");

        return storedHouse.getId();
    }

    @Override
    public List<HouseResponse> getAllHouses() {
        return houseRepository.findAll().stream()
                .map(house -> houseResponseConverter.convert(house))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteHouse(Long id) {
        House house = houseRepository.findById(id).orElseThrow(()
                -> new HouseNotFoundException(String.valueOf(id)));

        houseRepository.delete(house);
    }

    @Override
    public HouseResponse getHouse(Long id) {
        House house = houseRepository.findById(id).orElseThrow(()
                -> new HouseNotFoundException(String.valueOf(id)));

        return houseResponseConverter.convert(house);
    }

}
