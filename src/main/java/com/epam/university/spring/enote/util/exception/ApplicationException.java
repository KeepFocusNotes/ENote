package com.epam.university.spring.enote.util.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
public class ApplicationException extends RuntimeException {
    public static final String EXCEPTION_MODIFICATION_PROHIBITION = "exception.entity" +
            ".modificationProhibition";
    private final ErrorType type;
    private final String msgCode;
    private final HttpStatus httpStatus;
    private final String[] args;

    public ApplicationException(String msgCode, HttpStatus httpStatus) {
        this(ErrorType.APP_ERROR, msgCode, httpStatus);
    }

    public ApplicationException(ErrorType type, String msgCode, HttpStatus httpStatus,
                                String... args) {
        super(String.format("type=%s, msgCode=%s, args=%s", type, msgCode, Arrays.toString(args)));
        this.type = type;
        this.msgCode = msgCode;
        this.httpStatus = httpStatus;
        this.args = args;
    }
}
