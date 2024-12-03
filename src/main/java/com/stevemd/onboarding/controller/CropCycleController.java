package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.model.cropcycle.CropCycle;
import com.stevemd.onboarding.service.CropCycleService;
import com.stevemd.onboarding.wrappers.response.CropCycleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Controller
@RequestMapping(path = "/agrisasa/cropcycles/")
public class CropCycleController {

    private final CropCycleService cropCycleService;

    @Autowired
    public CropCycleController(CropCycleService cropCycleService) {
        this.cropCycleService = cropCycleService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "createdcropcycles")
    public List<CropCycle> createdFarmFields() {
        return cropCycleService.cropCycles();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "create")
    public CropCycleResponse createANewCropCycle(@RequestBody CropCycle cropCycle) {
        return cropCycleService.addNewCropCycle(cropCycle);
    }
}
