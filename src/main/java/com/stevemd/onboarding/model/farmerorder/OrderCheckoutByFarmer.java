package com.stevemd.onboarding.model.farmerorder;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ordersbyfarmer")
public class OrderCheckoutByFarmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    // Mapping the cart items with a One-to-Many relationship
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<FarmInputAgroDealerCartItem> cartOrder;

    private String farmerLocation;
    private String farmEmail;
    private String agrodealerID;
    private String orderStatus; // e.g., "PENDING", "APPROVED", "COMPLETED"
    private Double totalOrderInMoney;
}
