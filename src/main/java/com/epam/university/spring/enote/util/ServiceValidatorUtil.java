package com.epam.university.spring.enote.util;

import com.epam.university.spring.enote.util.exception.NotFoundException;

public class ServiceValidatorUtil {
    public static void validateNotFound(boolean conditionToCheck, String toPrint) {
        if (!conditionToCheck) {
            throw new NotFoundException(toPrint);
        }
    }

    public static void validateNotFoundWithId(boolean conditionToCheck, Integer id) {
        validateNotFound(conditionToCheck, "id=" + id);
    }

    public static <T> T validateNotFound(T objectToCheck, String toPrint) {
        validateNotFound(objectToCheck != null, toPrint);
        return objectToCheck;
    }

    public static <T> T validateNotFountWithId(T objectToCheck, Integer id) {
        return validateNotFound(objectToCheck, "id=" + id);
    }
}
