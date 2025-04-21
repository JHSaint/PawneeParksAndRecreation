package com.me.ServiceTests;

import com.me.Models.Customer;
import com.me.Models.Entities.CustomerEntity;
import com.me.Models.Requests.CreateCustomerRequest;
import com.me.Repositories.CustomerRepository;
import com.me.Services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTests {

    @Autowired
    CustomerService customerService;

    @MockitoBean
    CustomerRepository customerRepository;

    private CreateCustomerRequest request;
    private Customer customer;
    private CustomerEntity entity;

    private  final String testName = "Test Name";
    private  final String testEmail = "testEmail@email.com";
    private  final String testPhoneNumber = "123456789";

    @BeforeEach
    void setUp(){
        request = new CreateCustomerRequest(testName,"",testEmail,testPhoneNumber);
        entity = new CustomerEntity(request);
        customer = new Customer(entity);

    }

    @Test
    void whenGetAllCustomers_withExistingCustomers_shouldReturnCustomers(){
        ArrayList<CustomerEntity> entities = new ArrayList<>(Arrays.asList(entity));
        when(customerRepository.findAll()).thenReturn(entities);
        ArrayList<Customer> customers = customerService.getAllCustomers();
        System.out.println(customers.getFirst().getCustomerType());

        assertEquals(1,customers.size());
    }

    @Test
    void whenCreateCustomer_withValidRequest_shouldReturnCREATED(){
        when(customerRepository.findByEmail(customer.getEmail())).thenReturn(java.util.Optional.ofNullable(entity));
        ResponseEntity<String> response = customerService.createCustomer(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void whenDeleteCustomer_thatExist_shouldReturnOK(){
        when(customerRepository.findByEmail(customer.getEmail())).thenReturn(java.util.Optional.ofNullable(entity));
        ResponseEntity<String> response = customerService.deleteCustomer(testEmail);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


//    test for fetching one customer by email
//    @Test
//    void something(){
//
//    }


//    test for insights
//    @Test
//    void something(){
//
//    }
}
