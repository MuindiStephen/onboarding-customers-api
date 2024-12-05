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

    @ElementCollection
    @CollectionTable(name = "order_cart_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<FarmInputAgroDealerCartItem> cartOrder;

    private String farmerLocation;
    private String farmEmail;
    private String agrodealerID;
    private String orderStatus;
    private Double totalOrderInMoney;

}
