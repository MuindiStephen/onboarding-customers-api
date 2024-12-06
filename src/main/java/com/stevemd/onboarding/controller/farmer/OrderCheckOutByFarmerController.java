package com.stevemd.onboarding.controller.farmer;


import com.stevemd.onboarding.model.farmerorder.OrderCheckoutByFarmer;
import com.stevemd.onboarding.responses.CommonResponse;
import com.stevemd.onboarding.service.farmer.OrderCheckoutByFarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agrisasa/")
public class OrderCheckOutByFarmerController {

    private final OrderCheckoutByFarmerService orderService;

    @Autowired
    public OrderCheckOutByFarmerController(OrderCheckoutByFarmerService orderService) {
        this.orderService = orderService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("farmer/orders/placeorder")
    public CommonResponse placeAnOrder(@RequestBody OrderCheckoutByFarmer order) {
        return orderService.placeOrder(order);
    }

    // Get specific orders for an AgroDealer ID placed by a farmer
    @CrossOrigin(origins = "*")
    @GetMapping("farmer/orders/agrodealer/specificorders/{id}")
    public List<OrderCheckoutByFarmer> getSpecificOrdersForAgrodealerID(@PathVariable("id") String agrodealerId) {
        return orderService.getSpecificOrdersForAgrodealerID(agrodealerId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("farmer/orders/allordersplaced")
    public List<OrderCheckoutByFarmer> getAllOrdersToTheFarmer() {
        return orderService.getAllOrdersToTheFarmer();
    }


    @CrossOrigin(origins = "*")
    @DeleteMapping("farmer/orders/deleteorder/{id}")
    public CommonResponse deleteOrderAsAFarmer(@PathVariable Long id) {
        return orderService.cancelOrder(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("farmer/orders/{id}")
    public OrderCheckoutByFarmer getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("farmer/orders/updatestatus/{id}")
    public CommonResponse updateOrderStatus(@RequestParam String agrodealerID, @RequestParam String newStatus) {
        return orderService.updateOrderStatus(agrodealerID, newStatus);
    }
}
