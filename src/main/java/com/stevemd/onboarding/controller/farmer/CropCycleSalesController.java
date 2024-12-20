package com.stevemd.onboarding.controller.farmer;

import com.stevemd.onboarding.model.cropcyclefinancialdata.CropCycleSales;
import com.stevemd.onboarding.responses.CommonResponse;
import com.stevemd.onboarding.service.farmer.CropCycleSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping(path = "/agrisasa/")
public class CropCycleSalesController {

    private final CropCycleSalesService cropCycleSalesService;

    @Autowired
    public CropCycleSalesController(CropCycleSalesService cropCycleSalesService) {
        this.cropCycleSalesService = cropCycleSalesService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "cropcycles/trackingfinancialrecords/cropcyclesales")
    public List<CropCycleSales> allCropCycleSales() {
        return cropCycleSalesService.cropCycleSales();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "cropcycles/trackingfinancialrecords/cropcyclesales/create")
    public CommonResponse createACropCycleSaleRecords(@RequestBody CropCycleSales cropCycleSales) {
        return cropCycleSalesService.createANewCropCycleSaleRecord(cropCycleSales);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "cropcycles/trackingfinancialrecords/cropcyclesales/delete")
    public CommonResponse deleteAllCropCycleExpenses() {
        return cropCycleSalesService.deleteCycleSales();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("cropcycles/trackingfinancialrecords/cropcyclesales/{cropName}")
    public CommonResponse getTotalExpensesForSpecificCrop(@PathVariable String cropName) {
        return cropCycleSalesService.getTotalSalesForCrop(cropName);
    }
}

