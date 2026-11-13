package com.foodfarm.order.utility;

import lombok.Data;

@Data 
public class ErrorInfo {
    private Integer errorCode;
    private String errorMessage;
}
