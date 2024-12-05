package com.stevemd.onboarding.service;

import com.stevemd.onboarding.model.farmproducts.RegisterFarmProducts;
import com.stevemd.onboarding.repository.RegisterFarmProductsRepository;
import com.stevemd.onboarding.responses.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RegisterFarmProductsService {

    private final RegisterFarmProductsRepository registerFarmProductsRepository;

    @Autowired
    public RegisterFarmProductsService(RegisterFarmProductsRepository registerFarmProductsRepository) {
        this.registerFarmProductsRepository = registerFarmProductsRepository;
    }

    public List<RegisterFarmProducts> farmProducts() {
        return registerFarmProductsRepository.findAll();
    }

    // add a new farm field
    public CommonResponse registerNewFarmProduct(RegisterFarmProducts registerFarmProducts) {

        registerFarmProductsRepository.save(registerFarmProducts);

        System.out.println("Registered new farm product: "+registerFarmProducts);

        log.info(String.valueOf(HttpStatus.CREATED));

        return CommonResponse.builder()
                .status("00")
                .message("Farm Product is added successfully.")
                .build();

    }
}
