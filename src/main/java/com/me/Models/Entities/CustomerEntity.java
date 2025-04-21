package com.me.Models.Entities;

import com.me.Enums.CustomerType;
import com.me.Models.Requests.CreateCustomerRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue
    private String name;
    private String email;
    private String phoneNumber;
    private CustomerType customerType;
    private LocalDate lastContacted;

    public CustomerEntity(CreateCustomerRequest request){
        this.name = request.getName();
        this.email = request.getEmail();
        this.phoneNumber = request.getPhoneNumber();
        switch (request.getCustomerType()) {
            case "Community Member":
                this.customerType = CustomerType.COMMUNITY_MEMBER;
                break;
            case "Vendor":
                break;
            case "Local Business":
                break;
            default:
                this.customerType = CustomerType.COMMUNITY_MEMBER;
                break;
        }
        this.lastContacted = LocalDate.now();

    }
}
