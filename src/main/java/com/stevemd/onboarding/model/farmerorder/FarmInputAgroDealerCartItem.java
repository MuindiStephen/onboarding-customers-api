package com.stevemd.onboarding.model.farmerorder;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
@Data
@Entity
@Table(name = "farm_input_agrodealer_cart_items")
@AllArgsConstructor
@NoArgsConstructor
public class FarmInputAgroDealerCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private AgroDealerOffers offerProduct;

    private Integer quantity;
}

