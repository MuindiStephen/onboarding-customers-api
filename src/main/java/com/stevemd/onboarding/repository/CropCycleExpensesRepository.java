package com.stevemd.onboarding.repository;


import com.stevemd.onboarding.model.cropcyclefinancialdata.CropCycleExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CropCycleExpensesRepository extends JpaRepository<CropCycleExpenses, Long> {

    @Query("SELECT f.amountSpent FROM CropCycleExpenses f WHERE f.nameOfCropCycle = :cropName")
    String findTotalExpensesForCrop(String cropName);

    void deleteAll();
}

