package com.stevemd.onboarding.controller;


import com.stevemd.onboarding.model.farmproducts.RegisterFarmProducts;
import com.stevemd.onboarding.responses.CommonResponse;
import com.stevemd.onboarding.service.FileStorageService;
import com.stevemd.onboarding.service.RegisterFarmProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping(path = "/agrisasa/farmproducts/")
public class RegisterFarmProductsController {

    private final RegisterFarmProductsService registerFarmProductsService;

    private final FileStorageService fileStorageService;

    @Autowired
    public RegisterFarmProductsController(
            RegisterFarmProductsService registerFarmProductsService,
            FileStorageService fileStorageService) {
        this.registerFarmProductsService = registerFarmProductsService;
        this.fileStorageService = fileStorageService;
    }

    /**
     * @return Return a list of available farm products available to the buyer.
     */
    @CrossOrigin(origins = "*")
    @GetMapping(path = "all")
    public List<RegisterFarmProducts> allFarmProducts() {
        return registerFarmProductsService.farmProducts();
    }

    /**
     *
     * @param productName
     * @param pricePerUnit
     * @param quantity
     * @param category
     * @param farmerLocation
     * @param farmerContactDetail
     * @param productImage
     * @return
     */
    @CrossOrigin(origins = "*")
    @PostMapping(path = "add", consumes = {"multipart/form-data"})
    public CommonResponse registerNewFarmProduct(
            @RequestParam("productName") String productName,
            @RequestParam("pricePerUnit") double pricePerUnit,
            @RequestParam("quantity") int quantity,
            @RequestParam("category") String category,
            @RequestParam("farmerLocation") String farmerLocation,
            @RequestParam("farmerContactDetail") String farmerContactDetail,
            @RequestParam("productImage") MultipartFile productImage
    ) {
        try {
            String imageUrl = fileStorageService.storeFile(productImage);

            // Create and save the product
            RegisterFarmProducts product = new RegisterFarmProducts();
            product.setProductName(productName);
            product.setPricePerUnit(pricePerUnit);
            product.setQuantity(quantity);
            product.setCategory(category);
            product.setFarmerLocation(farmerLocation);
            product.setFarmerContactDetail(farmerContactDetail);
            product.setProductImageUrl(imageUrl);

            return registerFarmProductsService.registerNewFarmProduct(product);

        } catch (Exception e) {
            return CommonResponse.builder()
                    .status("00")
                    .message("Farm Product is added successfully.")
                    .build();
        }
    }
}
