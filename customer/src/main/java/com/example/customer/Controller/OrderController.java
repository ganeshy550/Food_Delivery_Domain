package com.example.customer.Controller;

import com.example.customer.Dto.AuthRequest;
import com.example.customer.Filters.JwtAuthFilter;
import com.example.customer.Model.Customer;
import com.example.customer.Model.CustomerOrder;
import com.example.customer.Repository.CustomerOrderRespository;
import com.example.customer.Repository.CustomerRepository;
import com.example.customer.Service.CustomerOrderService;
import com.example.customer.Service.CustomerService;
import com.example.customer.Service.JwtService;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class OrderController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private JwtAuthFilter authFilter;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenicationManager;

    @PostMapping("/add")
    public String addNewCustomer(@RequestBody @Valid Customer customer)
    {
        return customerService.addCustomer(customer);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/update/{id}")
    public Customer updateUser(@RequestBody Customer customer , @PathVariable Long id) throws Exception {
        return customerService.updateCustomer(id,customer);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/allorders")
    public List<CustomerOrder> getAllOrders() {
        return customerOrderService.getAllOrders();
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/order")
    public CustomerOrder createOrder(@RequestBody @Valid CustomerOrder order) {
        return customerOrderService.createOrder(order);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/order/{id}")
    public CustomerOrder getOrderById(@PathVariable Long id) {
        return customerOrderService.getOrderById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PutMapping("/order/{id}")
    public CustomerOrder updateOrder(@PathVariable Long id, @RequestBody @Valid CustomerOrder updatedOrder) {
        return customerOrderService.updateOrder(id, updatedOrder);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable Long id) {
        customerOrderService.deleteOrder(id);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenicationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getCustomerName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getCustomerName());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/orders/by-restaurant")
    public List<CustomerOrder> getOrdersByRestaurant(@RequestParam String restaurantName) {
        return customerOrderService.getOrdersByRestaurantName(restaurantName);
    }







}
