package com.stevemd.onboarding.repository;

import com.stevemd.onboarding.model.cropcyclefinancialdata.CropCycleSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CropCycleSalesRepository extends JpaRepository<CropCycleSales, Long> {

    @Query("SELECT f.totalSalesMade FROM CropCycleSales f WHERE f.cropCycleName = :cropName")
    String findTotalSalesForCrop(String cropName);

    void deleteAll();

}
