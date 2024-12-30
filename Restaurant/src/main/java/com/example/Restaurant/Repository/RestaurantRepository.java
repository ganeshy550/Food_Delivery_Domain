package com.example.Restaurant.Repository;

import com.example.Restaurant.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant , Long> {
    Optional<Restaurant> findByOwnerName(String ownerName);
}
