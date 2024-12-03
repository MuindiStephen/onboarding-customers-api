package com.stevemd.onboarding.model.cropcyclefinancialdata;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "crop_cycle_sales_records")
public class CropCycleSales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cropCycleName;

    @Column(nullable = false, unique = true)
    private String harvestedBags;

    @Column(nullable = false)
    private String soldBags;

    @Column(nullable = false)
    private String totalSalesMade;
}
