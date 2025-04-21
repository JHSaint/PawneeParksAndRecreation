package com.me.ControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.Models.Customer;
import com.me.Models.Entities.CustomerEntity;
import com.me.Models.Requests.CreateCustomerRequest;
import com.me.Services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerService customerService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private CreateCustomerRequest mockRequest =
            new CreateCustomerRequest("Test Customer", "test@test.com", "123456789","");
    private CustomerEntity mockEntity = new CustomerEntity(mockRequest);
    private Customer mockCustomer = new Customer((mockEntity));

    @Test
    void whenGetAllCustomers_returnAllCustomers() throws Exception {
        ArrayList<Customer> mockCustomers = new ArrayList<Customer>(Arrays.asList(mockCustomer));
        when(customerService.getAllCustomers()).thenReturn(mockCustomers);

        mockMvc.perform(get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Test Customer"));
    }

    @Test
    void whenCreateCustomer_withValidRequest_returnCREATED() throws Exception {
        when(customerService.createCustomer(any(CreateCustomerRequest.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Customer Created!"));

        mockMvc.perform(post("/customers/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenDeleteCustomer_thatExist_returnOK() throws Exception {
        when(customerService.deleteCustomer(any(String.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.OK).body("Customer Deleted!"));

        mockMvc.perform(delete("/customers/delete/{email}",mockRequest.getEmail())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockRequest)))
                .andExpect(status().isOk());
    }
}
