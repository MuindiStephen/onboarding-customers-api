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
@Table(name = "crop_cycle_expenses_records")
public class CropCycleExpenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nameOfCropCycle;

    @Column(nullable = false, unique = true)
    private String amountSpent;

    @Column(nullable = false)
    private String whichTask;

    @Column(nullable = false)
    private String expenseName;

    @Column(nullable = false)
    private String dateOfThisFinancialRecord;
}
