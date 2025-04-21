package com.me.Models.Requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
public class CreateCustomerRequest {
    @NotBlank
    private String name;
    @NotBlank
    @UniqueElements
    private String email;
    @NotBlank
    private String phoneNumber;

    private String customerType;
}
