package com.example.Restaurant.Config;

import com.example.Restaurant.Model.Restaurant;
import com.example.Restaurant.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserInfoUserDetailService implements UserDetailsService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Restaurant> customerInfo = restaurantRepository.findByOwnerName(username);
        return customerInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}
