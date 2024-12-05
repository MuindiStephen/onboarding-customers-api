package com.stevemd.onboarding.model.farmerorder;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Data
@Table(name = "agrodealeroffers")
@AllArgsConstructor
@NoArgsConstructor
public class AgroDealerOffers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Double originalPrice;
    private Double discountedPrice;
    private String discountPercentage;
    private String productImageUrl;
}

