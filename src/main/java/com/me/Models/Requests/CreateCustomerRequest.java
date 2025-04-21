package com.me.Models.Requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@AllArgsConstructor
@NoArgsConstructor
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
