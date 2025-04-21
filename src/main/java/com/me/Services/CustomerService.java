package com.me.Services;

import com.me.Models.Customer;
import com.me.Models.Entities.CustomerEntity;
import com.me.Models.Requests.CreateCustomerRequest;
import com.me.Repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public ArrayList<Customer> getAllCustomers(){
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try{
            ArrayList<CustomerEntity> customerEntities = customerRepository.findAll();
            if(customerEntities.size() == 0){
                log.info("There are no customers :(");
            }else{
                log.info("Successfully fetched customers!");
            }
        customers = customerEntities.stream()
                .map(Customer::new)
                .collect(Collectors.toCollection(ArrayList::new));
        }catch (Exception e){
            log.error("Error fetching customers. {}", e.getLocalizedMessage());
        }finally {
            return customers;
        }
    }

    public Customer getCustomer(String email){
        try{
            Optional<CustomerEntity> customer = customerRepository.findByEmail(email);
            if (customer.isEmpty()){
                log.info("Customer does not exist!");
                return null;
            }
            log.info("Customer {} found!", email);
            return new Customer(customer.get());
        }catch (Exception e){
            log.error("Error fetching customer. {}", e.getLocalizedMessage());
            return null;
        }
    }

    public ResponseEntity<String> createCustomer(CreateCustomerRequest request){
        try{
            Optional<CustomerEntity> customer = customerRepository.findByEmail(request.getEmail());

            if (customer.isPresent()){
                log.info("Customer already exist!");
                return ResponseEntity.ok("Customer already exist!");
            }

            customerRepository.save(new CustomerEntity(request));
            return ResponseEntity.ok("Customer created!");
        }catch (Exception e) {
            log.error("Error creating customer. See {}", e.getLocalizedMessage());
            return ResponseEntity.badRequest().body("Invalid customer data.");
        }
    }

    public ResponseEntity<String> deleteCustomer(String email){
        try{
            Optional<CustomerEntity> customer = customerRepository.findByEmail(email);

            if (customer.isEmpty()){
                log.info("Customer does not exist!");
                return ResponseEntity.ok("Customer does not exist!");
            }

            customerRepository.delete(customer.get());
            return ResponseEntity.ok("Customer deleted!");
        }catch (Exception e) {
            log.error("Error deleting customer. See {}", e.getLocalizedMessage());
            return ResponseEntity.badRequest().body("Error occurred while deleting customer.");
        }
    }

    public String getInsight(String email){
        //Potential logic: check customers last contacted value and based on some time bracket
        //provide different values to insight based on how long ago that last contacted date is


        //Thought, why not do it on a schedule and add it as a value to the model?
        //Probably better to just do the logic in memory on call
        return "This customer hasn’t interacted with Parks & Rec in a while. Consider re-engagement with a local initiative.";
    }
}
