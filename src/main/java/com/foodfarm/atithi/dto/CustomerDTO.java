package com.foodfarm.atithi.dto;

import lombok.Data;

@Data 
public class CustomerDTO {
    
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private AddressDTO address;

}
