package com.foodfarm.street.utility;


public class ErrorInfo {
    private String errorMessage;
    private Integer errorCode;

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }       

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
