package com.stevemd.onboarding.model.agrodealer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "agrodealers")
@NoArgsConstructor
@AllArgsConstructor
public class AgroDealer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String agrodealerName;

    @Column(nullable = false, unique = true)
    private String agrodealerEmailAddress;

    @Column(nullable = false)
    private String agrodealerPhysicalAddress;

    @Column(nullable = false)
    private String agrodealerLocation;

    @Column(nullable = false)
    private String agrodealerFarmInputsType;

    @Column(nullable = false)
    private String agrodealerFarmInputProductName;

    @Column(nullable = false)
    private String agrodealerFarmInputProductCost;
}