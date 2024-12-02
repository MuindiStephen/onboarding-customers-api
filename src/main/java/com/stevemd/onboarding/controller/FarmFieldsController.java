package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.model.farmfield.FarmField;
import com.stevemd.onboarding.service.FarmFieldsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping(path = "/agrisasa/farmfields/")
public class FarmFieldsController {

    private final FarmFieldsService farmFieldsService;

    @Autowired
    public FarmFieldsController(FarmFieldsService farmFieldsService) {
        this.farmFieldsService = farmFieldsService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "createdfarmfields")
    public List<FarmField> createdFarmFields() {
        return farmFieldsService.farmFields();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "create")
    public void createANewFarmField(@RequestBody FarmField farmField) {
        farmFieldsService.addNewFarmField(farmField);
    }
}
