package com.stevemd.onboarding.controller.farmer;


import com.stevemd.onboarding.model.cropcyclefinancialdata.CropCycleExpenses;
import com.stevemd.onboarding.responses.CommonResponse;
import com.stevemd.onboarding.service.farmer.CropCycleExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping(path = "/agrisasa/")
public class CropCycleExpensesController {

    private final CropCycleExpensesService cropCycleExpensesService;

    @Autowired
    public CropCycleExpensesController(CropCycleExpensesService cropCycleExpensesService) {
        this.cropCycleExpensesService = cropCycleExpensesService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "cropcycles/trackingfinancialrecords/cropcycleexpenses")
    public List<CropCycleExpenses> allCropCycleExpenses() {
        return cropCycleExpensesService.cropCycleExpenses();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "cropcycles/trackingfinancialrecords/cropcycleexpenses/create")
    public CommonResponse createACropCycleExpense(@RequestBody CropCycleExpenses cropCycleExpenses) {
        return cropCycleExpensesService.createANewCropCycleExpense(cropCycleExpenses);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "cropcycles/trackingfinancialrecords/cropcycleexpenses/delete")
    public CommonResponse deleteAllCropCycleExpenses() {
        return cropCycleExpensesService.deleteCycleExpenses();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("cropcycles/trackingfinancialrecords/cropcycleexpenses/{cropName}")
    public CommonResponse getTotalExpensesForSpecificCrop(@PathVariable String cropName) {
        return cropCycleExpensesService.getTotalExpensesForCrop(cropName);
    }
}
