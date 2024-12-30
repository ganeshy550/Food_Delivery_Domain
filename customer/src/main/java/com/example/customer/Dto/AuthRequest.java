package com.example.customer.Dto;

public class AuthRequest {

    private String customerName;

    private String password;

    public AuthRequest() {
    }

    public AuthRequest(String customerName, String password) {
        this.customerName = customerName;
        this.password = password;
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
}
