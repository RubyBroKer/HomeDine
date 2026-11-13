package com.foodfarm.order.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.foodfarm.order.exception.OrderException;

@RestControllerAdvice 
public class ExceptionControllerAdvice {


    private static final Log logger = LogFactory.getLog(ExceptionControllerAdvice.class);

    @ExceptionHandler (Exception.class)
    public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception){
        logger.error(exception.getMessage(), exception);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorMessage(exception.getMessage());
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ErrorInfo> meetingSchedulerExceptionHandler(OrderException orderException){
        logger.error(orderException.getMessage(), orderException);

        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorInfo.setErrorMessage(orderException.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
