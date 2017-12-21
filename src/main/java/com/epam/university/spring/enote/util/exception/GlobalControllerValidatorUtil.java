package com.epam.university.spring.enote.util.exception;

import com.epam.university.spring.enote.model.AbstractBaseEntity;
import org.springframework.http.HttpStatus;

import static com.epam.university.spring.enote.util.exception.ApplicationException.EXCEPTION_MODIFICATION_PROHIBITION;

public class GlobalControllerValidatorUtil {
    public static Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }

    public static void validateModificationAllowed(Integer id) {
        if (id < AbstractBaseEntity.START_SEQ) {
            throw new ApplicationException(EXCEPTION_MODIFICATION_PROHIBITION, HttpStatus
                    .UNAVAILABLE_FOR_LEGAL_REASONS);
        }
    }
}
