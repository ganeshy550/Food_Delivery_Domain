package com.example.Restaurant.Dto;

import java.util.List;

public class ResponsedDto {

    private RestaurantDto restaurantDto;



    private List<CustomerOrderDto> customerOrderDtos;

    public ResponsedDto() {
    }

    public ResponsedDto(RestaurantDto restaurantDto, List<CustomerOrderDto> customerOrderDtos) {
        this.restaurantDto = restaurantDto;
        this.customerOrderDtos = customerOrderDtos;
    }

    public RestaurantDto getRestaurantDto() {
        return restaurantDto;
    }

    public void setRestaurantDto(RestaurantDto restaurantDto) {
        this.restaurantDto = restaurantDto;
    }

    public List<CustomerOrderDto> getCustomerOrderDtos() {
        return customerOrderDtos;
    }

    public void setCustomerOrderDtos(List<CustomerOrderDto> customerOrderDtos) {
        this.customerOrderDtos = customerOrderDtos;
    }
}
