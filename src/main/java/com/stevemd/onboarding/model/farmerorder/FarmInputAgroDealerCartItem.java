package com.stevemd.onboarding.model.farmerorder;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Embeddable
@Getter
@Setter
@Data
@Entity
@Table(name = "farm_input_agrodealer_cart_items")
@AllArgsConstructor
@NoArgsConstructor
public class FarmInputAgroDealerCartItem {
    @OneToOne
    private AgroDealerOffers offerProduct;

    private Integer quantity;
}

