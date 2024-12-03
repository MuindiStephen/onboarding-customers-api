package com.stevemd.onboarding.service;


import com.stevemd.onboarding.model.cropcycle.CropCycle;
import com.stevemd.onboarding.repository.CropCycleRepository;
import com.stevemd.onboarding.wrappers.response.CropCycleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CropCycleService {


    private final CropCycleRepository cropCycleRepository;

    @Autowired
    public CropCycleService(CropCycleRepository cropCycleRepository) {
        this.cropCycleRepository = cropCycleRepository;
    }

    public List<CropCycle> cropCycles() {
        return cropCycleRepository.findAll();
    }

    // add a new crop cycle
    public CropCycleResponse addNewCropCycle(CropCycle cropCycle) {

        Optional<CropCycle> cropCycleByName =
                cropCycleRepository.findFarmFieldByCropName(cropCycle.getCropName());

        if (cropCycleByName.isPresent()) {

            return CropCycleResponse.builder()
                    .status("1")
                    .message("Crop cycle already exist.")
                    .build();
        }


        cropCycleRepository.save(cropCycle);

        System.out.println("Created a new crop cycle : "+cropCycle);

        log.info(String.valueOf(HttpStatus.CREATED));

        return CropCycleResponse.builder()
                .status("00")
                .message("Crop cycle created successfully.")
                .build();

    }

}
