package com.example.customer.Repository;

import com.example.customer.Model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRespository extends JpaRepository<CustomerOrder,Long> {

    List<CustomerOrder> findByRestaurantName(String restaurantName);

}
