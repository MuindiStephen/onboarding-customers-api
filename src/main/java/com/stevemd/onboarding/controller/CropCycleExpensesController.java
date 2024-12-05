package com.stevemd.onboarding.controller;


import com.stevemd.onboarding.model.cropcyclefinancialdata.CropCycleExpenses;
import com.stevemd.onboarding.responses.CommonResponse;
import com.stevemd.onboarding.service.CropCycleExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping(path = "/agrisasa/cropcycles/trackingfinancialrecords/")
public class CropCycleExpensesController {

    private final CropCycleExpensesService cropCycleExpensesService;

    @Autowired
    public CropCycleExpensesController(CropCycleExpensesService cropCycleExpensesService) {
        this.cropCycleExpensesService = cropCycleExpensesService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "cropcycleexpenses")
    public List<CropCycleExpenses> allCropCycleExpenses() {
        return cropCycleExpensesService.cropCycleExpenses();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "cropcycleexpenses/create")
    public CommonResponse createACropCycleExpense(@RequestBody CropCycleExpenses cropCycleExpenses) {
        return cropCycleExpensesService.createANewCropCycleExpense(cropCycleExpenses);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "cropcycleexpenses/delete")
    public CommonResponse deleteAllCropCycleExpenses() {
        return cropCycleExpensesService.deleteCycleExpenses();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("cropcycleexpenses/{cropName}")
    public CommonResponse getTotalExpensesForSpecificCrop(@PathVariable String cropName) {
        return cropCycleExpensesService.getTotalExpensesForCrop(cropName);
    }
}
