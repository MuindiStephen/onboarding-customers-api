package com.stevemd.onboarding.service;


import com.stevemd.onboarding.model.cropcycle.CropCycle;
import com.stevemd.onboarding.model.cropcyclefinancialdata.CropCycleExpenses;
import com.stevemd.onboarding.model.farmfield.FarmField;
import com.stevemd.onboarding.repository.CropCycleExpensesRepository;
import com.stevemd.onboarding.repository.FarmFieldsRepository;
import com.stevemd.onboarding.responses.CommonResponse;
import com.stevemd.onboarding.wrappers.response.CropCycleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CropCycleExpensesService {

    private final CropCycleExpensesRepository cropCycleExpensesRepository;

    @Autowired
    public CropCycleExpensesService(CropCycleExpensesRepository cropCycleExpensesRepository) {
        this.cropCycleExpensesRepository = cropCycleExpensesRepository;
    }

    public List<CropCycleExpenses> cropCycleExpenses() {
        return cropCycleExpensesRepository.findAll();
    }

    // add a new farm field
    public CommonResponse createANewCropCycleExpense(CropCycleExpenses cropCycleExpense) {

        cropCycleExpensesRepository.save(cropCycleExpense);

        System.out.println("Created a new crop cycle: "+cropCycleExpense);

        log.info(String.valueOf(HttpStatus.CREATED));

        return CommonResponse.builder()
                .status("00")
                .message("Crop cycle Expense created successfully.")
                .build();
    }

    public CommonResponse deleteCycleExpenses() {
        cropCycleExpensesRepository.deleteAll();
        return CommonResponse.builder()
                .status("00")
                .message("Expense records deleted successfully.")
                .build();
    }

    public CommonResponse getTotalExpensesForCrop(String cropName) {
        cropCycleExpensesRepository.findTotalExpensesForCrop(cropName);
        return CommonResponse.builder()
                .status("00")
                .message(cropCycleExpensesRepository.findTotalExpensesForCrop(cropName))
                .build();

    }


}
