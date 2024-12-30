package com.example.Restaurant.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name="restaurant_info")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @NotBlank(message = "Restaurant cant be blank")
    private String restaurantName;

    @NotBlank(message = "Owner name cant be blank")
    private String ownerName;

    @NotBlank(message = "Password Cant Be Null")
    @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "Password must include at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    private String passWord;

    @NotBlank(message = "Email Cant Be Null")
    private String emailId;

    @NotBlank(message = "Address Cant Be Null")
    private String resturantAddress;

    @NotBlank(message = "Address Cant Be Null")
    private String taxNumber;

    @NotBlank(message = "Roles cannot be blank")
    @Pattern(regexp = "^(ROLE_ADMIN|ROLE_USER)$", message = "Role must be one of ROLE_ADMIN or ROLE_USER")
    private String roles;

    @NotBlank(message = "Todays Menu will be update ")
    private String menuItemsList;

    private Long orderId;

    public Restaurant() {
    }

    public Restaurant(Long restaurantId, String restaurantName, String ownerName, String passWord, String emailId, String resturantAddress, String taxNumber, String roles, String menuItemsList , Long orderId) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.ownerName = ownerName;
        this.passWord = passWord;
        this.emailId = emailId;
        this.resturantAddress = resturantAddress;
        this.taxNumber = taxNumber;
        this.roles = roles;
        this.menuItemsList = menuItemsList;
        this.orderId = orderId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getResturantAddress() {
        return resturantAddress;
    }

    public void setResturantAddress(String resturantAddress) {
        this.resturantAddress = resturantAddress;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getMenuItemsList() {
        return menuItemsList;
    }

    public void setMenuItemsList(String  menuItemsList) {
        this.menuItemsList = menuItemsList;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
