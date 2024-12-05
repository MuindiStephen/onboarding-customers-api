package com.stevemd.onboarding.service.farmer;


import com.stevemd.onboarding.model.cropcyclefinancialdata.CropCycleSales;
import com.stevemd.onboarding.repository.farmer.CropCycleSalesRepository;
import com.stevemd.onboarding.responses.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CropCycleSalesService {

    private final CropCycleSalesRepository cropCycleSalesRepository;

    @Autowired
    public CropCycleSalesService(CropCycleSalesRepository cropCycleSalesRepository) {
        this.cropCycleSalesRepository = cropCycleSalesRepository;
    }

    public List<CropCycleSales> cropCycleSales() {
        return cropCycleSalesRepository.findAll();
    }

    public CommonResponse createANewCropCycleSaleRecord(CropCycleSales cropCycleSales) {

        cropCycleSalesRepository.save(cropCycleSales);

        System.out.println("Created a new crop cycle sale record: "+cropCycleSales);

        log.info(String.valueOf(HttpStatus.CREATED));

        return CommonResponse.builder()
                .status("00")
                .message("Crop cycle sale record created successfully.")
                .build();
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
