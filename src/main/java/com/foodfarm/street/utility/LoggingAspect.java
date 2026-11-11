package com.foodfarm.street.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.foodfarm.street.entity.Restaurant;
import com.foodfarm.street.exception.RestaurantException;

public class LoggingAspect {
    private static final Log logger = LogFactory.getLog(LoggingAspect.class);

    public void logServiceException() throws RestaurantException{

    }
}
