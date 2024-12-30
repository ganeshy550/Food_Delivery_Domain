package com.example.customer.Service;

import com.example.customer.Model.CustomerOrder;
import com.example.customer.Repository.CustomerOrderRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderService {

    @Autowired
    private CustomerOrderRespository customerOrderRespository;

    public CustomerOrder createOrder(CustomerOrder order) {
        double totalPrice = order.getPrice() * order.getQuantity(); // Calculate total price
        order.setTotalPrice(totalPrice);
        return customerOrderRespository.save(order);
    }

    public Optional<CustomerOrder> getOrderById(Long id) {
        return customerOrderRespository.findById(id);
    }

    public List<CustomerOrder> getAllOrders() {
        return customerOrderRespository.findAll();
    }
    public CustomerOrder updateOrder(Long id, CustomerOrder updatedOrder) {
        return customerOrderRespository.findById(id).map(order -> {
            order.setItemName(updatedOrder.getItemName());
            order.setQuantity(updatedOrder.getQuantity());
            order.setPrice(updatedOrder.getPrice());
            order.setTotalPrice(updatedOrder.getPrice() * updatedOrder.getQuantity());
            return customerOrderRespository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }
    public void deleteOrder(Long id) {
        if (!customerOrderRespository.existsById(id)) {
            throw new RuntimeException("Order not found with ID: " + id);
        }
        customerOrderRespository.deleteById(id);
    }
    public List<CustomerOrder> getOrdersByRestaurantName(String restaurantName) {
        return customerOrderRespository.findByRestaurantName(restaurantName);
    }
}
