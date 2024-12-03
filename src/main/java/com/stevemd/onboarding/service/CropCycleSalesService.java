package com.stevemd.onboarding.service;


import com.stevemd.onboarding.model.cropcyclefinancialdata.CropCycleSales;
import com.stevemd.onboarding.repository.CropCycleSalesRepository;
import com.stevemd.onboarding.responses.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CropCycleSalesService {

    private final CropCycleSalesRepository cropCycleSalesRepository;

    @Autowired
    public CropCycleSalesService(CropCycleSalesRepository cropCycleSalesRepository) {
        this.cropCycleSalesRepository = cropCycleSalesRepository;
    }

    public List<CropCycleSales> cropCycleSales() {
        return cropCycleSalesRepository.findAll();
    }

    public CommonResponse deleteCycleSales() {
        cropCycleSalesRepository.deleteAll();
        return CommonResponse.builder()
                .status("00")
                .message("Sales records deleted successfully.")
                .build();
    }

    public CommonResponse getTotalSalesForCrop(String cropName) {
        cropCycleSalesRepository.findTotalSalesForCrop(cropName);
        return CommonResponse.builder()
                .status("00")
                .message(cropCycleSalesRepository.findTotalSalesForCrop(cropName))
                .build();

    }
}
