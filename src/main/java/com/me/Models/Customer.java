package com.me.Models;


import com.me.Enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String name;
    private String email;
    private String phoneNumber;
    private CustomerType customerType;
    private LocalDate lastContacted;

}
