package com.samuel.house.manager.error.util;

/**
 * Validator to check if house ids to delete or get houses are valid.
 */
public class ControllerValidationUtil {
    public static Long parseLongWithException(final String paramName, final String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Value of the field [" + paramName + "] is not a number: " + value);
        }
    }
}
