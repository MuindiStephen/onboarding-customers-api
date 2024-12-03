package com.stevemd.onboarding.controller;

import com.stevemd.onboarding.model.agrodealer.AgroDealer;
import com.stevemd.onboarding.responses.CommonResponse;
import com.stevemd.onboarding.service.AgroDealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/agrisasa/agrodealers/")
public class AgroDealerController {

    private final AgroDealerService agroDealerService;

    @Autowired
    public AgroDealerController(AgroDealerService agroDealerService) {
        this.agroDealerService = agroDealerService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "createdagrodealers")
    public List<AgroDealer> createdAgroDealers() {
        return agroDealerService.agroDealers();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "newagrodealer/create")
    public CommonResponse createANewAgroDealer(@RequestBody AgroDealer agroDealer) {
        return agroDealerService.addNewAgrodealer(agroDealer);
    }
}
