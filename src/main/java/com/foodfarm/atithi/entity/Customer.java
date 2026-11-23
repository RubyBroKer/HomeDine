package com.foodfarm.atithi.entity;

import com.foodfarm.atithi.dto.AddressDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity 
@Data 
public class Customer {
    @Id 
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "customerId")
    private AddressDTO address;

}
