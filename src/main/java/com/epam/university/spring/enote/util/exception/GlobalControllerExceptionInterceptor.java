package com.epam.university.spring.enote.util.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

import static com.epam.university.spring.enote.util.exception.ErrorType.APP_ERROR;

@ControllerAdvice(annotations = RestController.class)
@ResponseBody
public class GlobalControllerExceptionInterceptor {
    private static Logger log = LoggerFactory.getLogger(GlobalControllerExceptionInterceptor.class);


    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorInfo> applicationError(HttpServletRequest req, ApplicationException appEx) {
        ErrorInfo errorInfo = logAndGetErrorInfo(req, appEx, false, appEx.getType(), appEx.getMsgCode());
        return new ResponseEntity<>(errorInfo, appEx.getHttpStatus());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true, APP_ERROR);
    }

    private ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException, ErrorType errorType, String... details) {
        Throwable rootCause = GlobalControllerValidatorUtil.getRootCause(e);
        if (logException) {
            log.error(errorType + " at request " + req.getRequestURL(), rootCause);
        } else {
            log.warn(errorType + " at request " + req.getRequestURL(), rootCause);
        }
        return new ErrorInfo(req.getRequestURL(), errorType, errorType.getErrorCode(),
                details.length != 0 ? details : new String[]{rootCause.toString()});
    }
}