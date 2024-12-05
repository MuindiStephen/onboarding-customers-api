package com.stevemd.onboarding.controller.farmer;


import com.stevemd.onboarding.model.farmerorder.OrderCheckoutByFarmer;
import com.stevemd.onboarding.responses.CommonResponse;
import com.stevemd.onboarding.service.farmer.OrderCheckoutByFarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agrisasa/farmer/orders")
public class OrderCheckOutByFarmerController {

    private final OrderCheckoutByFarmerService orderService;

    @Autowired
    public OrderCheckOutByFarmerController(OrderCheckoutByFarmerService orderService) {
        this.orderService = orderService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/placeorder")
    public CommonResponse placeAnOrder(@RequestBody OrderCheckoutByFarmer order) {
        return orderService.placeOrder(order);
    }

    // Get specific orders for an AgroDealer ID placed by a farmer
    @CrossOrigin(origins = "*")
    @GetMapping("/agrodealer/specificorders/{id}")
    public List<OrderCheckoutByFarmer> getSpecificOrdersForAgrodealerID(@PathVariable("id") String agrodealerId) {
        return orderService.getSpecificOrdersForAgrodealerID(agrodealerId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allordersplaced")
    public List<OrderCheckoutByFarmer> getAllOrdersToTheFarmer() {
        return orderService.getAllOrdersToTheFarmer();
    }


    @CrossOrigin(origins = "*")
    @DeleteMapping("/deleteorder/{id}")
    public CommonResponse deleteOrderAsAFarmer(@PathVariable Long id) {
        return orderService.cancelOrder(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public OrderCheckoutByFarmer getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/updatestatus/{id}")
    public CommonResponse updateOrderStatus(@RequestParam String agrodealerID, @RequestParam String newStatus) {
        return orderService.updateOrderStatus(agrodealerID, newStatus);
    }
}
