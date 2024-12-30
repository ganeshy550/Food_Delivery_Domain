package com.example.Restaurant.Service;

import com.example.Restaurant.Dto.CustomerOrderDto;
import com.example.Restaurant.Dto.ResponsedDto;
import com.example.Restaurant.Dto.RestaurantDto;
import com.example.Restaurant.Model.Restaurant;
import com.example.Restaurant.Repository.RestaurantRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Autowired
    private WebClient webClient;

    // Create a new restaurant
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    // Get all restaurants
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // Get a specific restaurant by its id
    public Optional<Restaurant> getRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }

    // Update an existing restaurant
    public Restaurant updateRestaurant(Long restaurantId, Restaurant updatedRestaurant) {
        Optional<Restaurant> existingRestaurantOpt = restaurantRepository.findById(restaurantId);
        if (existingRestaurantOpt.isPresent()) {
            Restaurant existingRestaurant = existingRestaurantOpt.get();
            // Updating properties if needed
            existingRestaurant.setRestaurantName(updatedRestaurant.getRestaurantName());
            existingRestaurant.setOwnerName(updatedRestaurant.getOwnerName());
            existingRestaurant.setPassWord(updatedRestaurant.getPassWord());
            existingRestaurant.setEmailId(updatedRestaurant.getEmailId());
            existingRestaurant.setResturantAddress(updatedRestaurant.getResturantAddress());
            existingRestaurant.setTaxNumber(updatedRestaurant.getTaxNumber());
            existingRestaurant.setRoles(updatedRestaurant.getRoles());
            existingRestaurant.setMenuItemsList(updatedRestaurant.getMenuItemsList());
            return restaurantRepository.save(existingRestaurant);
        } else {
            throw new RuntimeException("Restaurant not found");
        }
    }

    // Delete a restaurant by its id
    public void deleteRestaurant(Long restaurantId) {
        Optional<Restaurant> existingRestaurantOpt = restaurantRepository.findById(restaurantId);
        if (existingRestaurantOpt.isPresent()) {
            restaurantRepository.delete(existingRestaurantOpt.get());
        } else {
            throw new RuntimeException("Restaurant not found");
        }
    }

    // Find restaurants by role (e.g., ROLE_ADMIN or ROLE_USER)
    public List<Restaurant> getRestaurantsByRole(String role) {
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurant.getRoles().equals(role))
                .toList();
    }

    @CircuitBreaker(name = "franchiseService", fallbackMethod = "fallbackGetRestaurant")
    public ResponsedDto getRestaurantDetails(Long franchiseId){
        ResponsedDto responsedto = new ResponsedDto();
        Restaurant restaurant = restaurantRepository.findById(franchiseId).orElseThrow(() -> new RuntimeException("Restaurent not found"));
        RestaurantDto restaurantDto = mapToRestaurntDto(restaurant);

        List<CustomerOrderDto> customerOrderDtos = webClient.get()
                .uri("http://localhost:2003/customer/orders/by-restaurant" +restaurant.getRestaurantName())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CustomerOrderDto>>(){})
                .block();

        responsedto.setRestaurantDto(restaurantDto);
        responsedto.setCustomerOrderDtos(customerOrderDtos);

        return responsedto;




    }

    public ResponsedDto fallbackGetRestaurant(Integer restaurantService, Throwable throwable) {
        // Handle the fallback logic, e.g., return a default response or log the error
        return new ResponsedDto(); // Return an empty response or a default response
    }

    public RestaurantDto mapToRestaurntDto(Restaurant restaurant){
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setRestaurantId(restaurant.getRestaurantId());
        restaurantDto.setRestaurantName(restaurant.getRestaurantName());
        restaurantDto.setResturantAddress(restaurant.getResturantAddress());
        restaurantDto.setOwnerName(restaurant.getOwnerName());


        return restaurantDto;
    }
}
