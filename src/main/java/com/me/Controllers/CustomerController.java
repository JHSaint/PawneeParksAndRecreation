package com.me.Controllers;

import com.me.Models.Customer;
import com.me.Models.Requests.CreateCustomerRequest;
import com.me.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ArrayList<Customer> getAllCustomers(){return customerService.getAllCustomers();}

    @GetMapping("{email}")
    public Customer getCustomer(@PathVariable String email) {return customerService.getCustomer(email);}

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody CreateCustomerRequest request){
        return customerService.createCustomer(request);
    }

    @DeleteMapping("delete/{email}")
    public  ResponseEntity<String> deleteCustomer(@PathVariable String email){
        return customerService.deleteCustomer(email);
    }

    @GetMapping("insight/{email}")
    public String getCustomerInsight(@PathVariable String email){
        return customerService.getInsight(email);
    }
}
