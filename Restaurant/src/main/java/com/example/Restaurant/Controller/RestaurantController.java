package com.example.Restaurant.Controller;

import com.example.Restaurant.Dto.AuthRequest;
import com.example.Restaurant.Dto.ResponsedDto;
import com.example.Restaurant.Model.Restaurant;
import com.example.Restaurant.Service.JwtService;
import com.example.Restaurant.Service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private AuthRequest authRequest;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Create a new restaurant (Admin only)

    @PostMapping("/add")
    public Restaurant createRestaurant(@RequestBody @Valid Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant);
    }

    // Get all restaurants (Admin only)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    // Get a specific restaurant by its id (Admin only)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public Optional<Restaurant> getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    // Update an existing restaurant (Admin only)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("update/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant) {
        return restaurantService.updateRestaurant(id, updatedRestaurant);
    }

    // Delete a restaurant by its id (Admin only)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("delete/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }


    // Get restaurant details and customer orders (Admin only)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/details/{restaurantId}")
    public ResponsedDto getRestaurantDetails(@PathVariable Long restaurantId) {
        return restaurantService.getRestaurantDetails(restaurantId);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest  authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getOwnerName(), authRequest.getPassWord()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getOwnerName());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
