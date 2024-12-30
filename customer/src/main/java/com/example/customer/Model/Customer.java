package com.example.customer.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "customer_details")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotBlank(message = "Name cant be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String customerName;

    @NotBlank(message = "Password Cant Be Null")
    @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "Password must include at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    private String password;
    @NotBlank(message = "Password Cant Be Null")
    @Email(message = "Email should be valid")
    private String emailId;

    @NotBlank(message = "Roles cannot be blank")
    @Pattern(regexp = "^(ROLE_ADMIN|ROLE_USER)$", message = "Role must be one of ROLE_ADMIN or ROLE_USER")
    private String roles;
    @NotBlank(message = "Number cannot be blank")
    @Size(min = 10, max = 12, message = "Phone number")
    private String phoneNumber;

    public Customer() {
    }

    public Customer(Long customerId, String customerName, String password, String emailId, String roles, String phoneNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.password = password;
        this.emailId = emailId;
        this.roles = roles;
        this.phoneNumber = phoneNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
