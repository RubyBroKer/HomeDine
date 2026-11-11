package com.foodfarm.street.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.error.Error;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.stream.Collectors;
import com.foodfarm.street.exception.RestaurantException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

public class ExceptionControllerAdvice {
    
    private static final Log LOGGER = LogFactory.getLog(ExceptionControllerAdvice.class);

    private Environment environment;

    public ResponseEntity<ErrorInfo> meetingSchedulerExceptionHandler(RestaurantException restaurantException){

        LOGGER.error(restaurantException.getMessage(), restaurantException);

        ErrorInfo errorInfo = new ErrorInfo();

        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorInfo.setErrorMessage(restaurantException.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception){
        LOGGER.error(exception.getMessage(), exception);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorInfo.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ErrorInfo> validatorExceptionHandler(Exception exception){
        LOGGER.error(exception.getMessage(), exception);

        ErrorInfo errorInfo = new ErrorInfo();
        String erroString;
        if( exception instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException manvException = (MethodArgumentNotValidException) exception;
            erroString = manvException.getBindingResult()
                            .getAllErrors()
                            .stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining(","));

        }
        else{
            ConstraintViolationException cvException = (ConstraintViolationException) exception;
            erroString = cvException.getConstraintViolations()
                        .stream()
                        .map(ConstraintViolation :: getMessage)
                        .collect(Collectors.joining(","));
        }

        errorInfo.setErrorMessage(erroString);
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
