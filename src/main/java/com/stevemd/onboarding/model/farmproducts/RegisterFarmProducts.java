package com.stevemd.onboarding.model.farmproducts;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class RegisterFarmProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private double pricePerUnit;
    private double quantity;
    private String category;
    private String farmerLocation; // e.g., "2.5km away"
    private String farmerContactDetail; // Phone number

    private String productImageUrl; // URL for the uploaded image
}
