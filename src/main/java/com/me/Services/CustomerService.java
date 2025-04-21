package com.me.Services;

import com.me.Models.Customer;
import com.me.Models.Requests.CreateCustomerRequest;
import com.me.Repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.ArrayList;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public ArrayList<Customer> getAllCustomers(){
        return new ArrayList<Customer>();
    }

    public Customer getCustomer(String email){
        return new Customer();
    }

    public ResponseEntity<String> createCustomer(CreateCustomerRequest request){
        return ResponseEntity.ok().body("OK");
    }

    public ResponseEntity<String> deleteCustomer(String email){
        return ResponseEntity.ok().body("OK");
    }

    public String getInsight(String email){
        return "";
    }
}
