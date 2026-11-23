package com.foodfarm.atithi.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.foodfarm.atithi.exception.CustomerException;

public class LoggingAspect {
    private static final Log logger = LogFactory.getLog(LoggingAspect.class);

    public void logServiceException() throws CustomerException{
        
    }

}
