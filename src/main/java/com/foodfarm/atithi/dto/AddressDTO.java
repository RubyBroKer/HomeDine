package com.foodfarm.atithi.dto;

import lombok.Data;

@Data 
public class AddressDTO {
    
    private Long addressId;
    private String addressLine1;
    private String addressLine2;
    private String landmark;
    private String city;
    private String state;
    private String pincode;
    private String latitude;
    private String longitude;
}
