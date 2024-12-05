package com.stevemd.onboarding.controller.fieldagent;

import com.stevemd.onboarding.model.agrodealer.AgroDealer;
import com.stevemd.onboarding.responses.CommonResponse;
import com.stevemd.onboarding.service.fieldagent.AgroDealerService;
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

    // Approve an agrodealer
    @CrossOrigin(origins = "*")
    @PutMapping(path = "approveagrodealer/{id}")
    public CommonResponse approveAnAgroDealer(@PathVariable Long id) {
        return agroDealerService.approveAgroDealer(id);
    }

    // Reject an agrodealer
    @CrossOrigin(origins = "*")
    @PutMapping(path = "rejectagrodealer/{id}")
    public CommonResponse rejectAnAgroDealer(@PathVariable Long id, @RequestBody String reason) {
        return agroDealerService.rejectAgroDealer(id, reason);
    }

    // Get all pending approvals
    @CrossOrigin(origins = "*")
    @GetMapping(path = "pendingapprovals")
    public List<AgroDealer> getPendingApprovals() {
        return agroDealerService.getPendingApprovals();
    }
}
