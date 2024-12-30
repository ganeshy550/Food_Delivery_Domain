package com.example.customer.Service;

import com.example.customer.Model.Customer;
import com.example.customer.Repository.CustomerRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addCustomer(Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
        return "Thanks for registering!";

    }

    public Customer updateCustomer(Long id , Customer customer) throws Exception{
        Customer oldCustomer = customerRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not FOund! Please Register"));
        oldCustomer.setCustomerName(customer.getCustomerName());
        oldCustomer.setEmailId(customer.getEmailId());
        oldCustomer.setPassword(customer.getPassword());

        customerRepository.save(oldCustomer);

        return oldCustomer;


    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }






}
