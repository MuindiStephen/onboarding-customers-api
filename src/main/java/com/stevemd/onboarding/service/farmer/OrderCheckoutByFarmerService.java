package com.stevemd.onboarding.service.farmer;

import com.stevemd.onboarding.model.farmerorder.OrderCheckoutByFarmer;
import com.stevemd.onboarding.repository.farmer.OrderCheckoutByFarmerRepository;
import com.stevemd.onboarding.responses.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderCheckoutByFarmerService {

    private final OrderCheckoutByFarmerRepository orderRepository;

    @Autowired
    public OrderCheckoutByFarmerService(OrderCheckoutByFarmerRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Add new cart item
    public CommonResponse placeOrder(OrderCheckoutByFarmer order) {
        orderRepository.save(order);

        log.info("Cart items were added and order was placed successfully.");
        return CommonResponse.builder()
                .status("00")
                .message("Order submitted successfully.")
                .build();
    }

    // Get all orders specific to an AgroDealer ID
    public List<OrderCheckoutByFarmer> getSpecificOrdersForAgrodealerID(String agrodealerId) {
        return orderRepository.findByAgrodealerID(agrodealerId);
    }

    // Retrieve all orders made by farmers
    public List<OrderCheckoutByFarmer> getAllOrdersToTheFarmer() {
        return orderRepository.findAll();
    }


    // Retrieve a specific order by ID
    public OrderCheckoutByFarmer getOrderById(Long id) {
        OrderCheckoutByFarmer order = orderRepository.findById(id).orElse(null);

        if (order == null) {
            CommonResponse.builder()
                    .status("1")
                    .message("Order not found.")
                    .build();
        }

        return order;
    }


    // Update order status
    public CommonResponse updateOrderStatus( String agrodealerId ,String newStatus) {

        List<OrderCheckoutByFarmer> orders = orderRepository.findByAgrodealerID(agrodealerId);

        if (orders.isEmpty()) {
            return CommonResponse.builder()
                    .status("1")
                    .message("No orders found for this AgroDealer ID")
                    .build();
        }

        for (OrderCheckoutByFarmer order : orders) {
            order.setOrderStatus(newStatus);
            orderRepository.save(order); // Save updated status
        }

        return CommonResponse.builder()
                .status("00")
                .message("Order statuses updated successfully")
                .build();
    }


    // Delete order by ID
    public CommonResponse cancelOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);

            return CommonResponse.builder()
                    .status("00")
                    .message("You cancelled order successfully.")
                    .build();
        } else {
            return CommonResponse.builder()
                    .status("01")
                    .message("Order not found.")
                    .build();
        }
    }
}
