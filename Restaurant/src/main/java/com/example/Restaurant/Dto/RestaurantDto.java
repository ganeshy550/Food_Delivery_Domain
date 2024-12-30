package com.example.Restaurant.Dto;

public class RestaurantDto {

    private Long restaurantId;

    private String restaurantName;

    private String ownerName;

    private String resturantAddress;

    public RestaurantDto() {
    }

    public RestaurantDto(Long restaurantId, String restaurantName, String ownerName, String resturantAddress) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.ownerName = ownerName;
        this.resturantAddress = resturantAddress;
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

    public String getResturantAddress() {
        return resturantAddress;
    }

    public void setResturantAddress(String resturantAddress) {
        this.resturantAddress = resturantAddress;
    }
}
