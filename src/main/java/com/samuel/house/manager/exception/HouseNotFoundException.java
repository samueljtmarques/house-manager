package com.samuel.house.manager.exception;

import static com.samuel.house.manager.model.Constants.HOUSE_NOT_FOUND;

public class HouseNotFoundException  extends RuntimeException {
    public HouseNotFoundException(String id) {
        super(String.format(HOUSE_NOT_FOUND, id));
    }
}
