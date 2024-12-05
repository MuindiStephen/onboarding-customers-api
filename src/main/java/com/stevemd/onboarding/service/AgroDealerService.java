package com.stevemd.onboarding.service;

import com.stevemd.onboarding.model.agrodealer.AgroDealer;
import com.stevemd.onboarding.repository.AgroDealerRepository;
import com.stevemd.onboarding.responses.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AgroDealerService {

    private final AgroDealerRepository agroDealerRepository;

    @Autowired
    public AgroDealerService(AgroDealerRepository agroDealerRepository) {
        this.agroDealerRepository = agroDealerRepository;
    }

    // Get a list of All Available Agro-dealers
    public List<AgroDealer> agroDealers() {
        return agroDealerRepository.findAll();
    }

    // Register a new agrodealer
    public CommonResponse addNewAgrodealer(AgroDealer agroDealer) {

        Optional<AgroDealer> agroDealerByAgrodealerName =
                agroDealerRepository.findAgroDealerByAgrodealerName(agroDealer.getAgrodealerName());

        if (agroDealerByAgrodealerName.isPresent()) {

            return CommonResponse.builder()
                    .status("1")
                    .message("Similar agrodealer exist!")
                    .build();
        }

        agroDealer.setStatus("Pending");
        agroDealerRepository.save(agroDealer);

        System.out.println("Created a new agrodealer: "+agroDealer);

        log.info(String.valueOf(HttpStatus.CREATED));

        return CommonResponse.builder()
                .status("00")
                .message("Agrodealer Created successfully.")
                .build();

    }

    // Approve created Agrodeealers
    // Approve an agrodealer
    public CommonResponse approveAgroDealer(AgroDealer agroDealer) {

        Optional<AgroDealer> agroDealerById = agroDealerRepository.findById(agroDealer.getId());

        if (!agroDealerById.isPresent()) {
            return CommonResponse.builder()
                    .status("1")
                    .message("Agrodealer not found")
                    .build();
        }


        if ("Approved".equalsIgnoreCase(agroDealer.getStatus())) {
            return CommonResponse.builder()
                    .status("00")
                    .message("Agrodealer is already approved.")
                    .build();
        }

        agroDealer.setStatus("Approved");
        agroDealerRepository.save(agroDealer);

        log.info("Approved agrodealer: {}", agroDealer);
        return CommonResponse.builder()
                .status("00")
                .message("Agrodealer approved successfully.")
                .build();
    }

    // Reject an agrodealer
    public CommonResponse rejectAgroDealer(Long id, String reason) {
        AgroDealer agroDealer = agroDealerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Agrodealer not found."));

        if ("Rejected".equalsIgnoreCase(agroDealer.getStatus())) {
            return CommonResponse.builder()
                    .status("1")
                    .message("Agrodealer is already rejected.")
                    .build();
        }

        agroDealer.setStatus("Rejected");
        agroDealer.setRejectionReason(reason);
        agroDealerRepository.save(agroDealer);

        log.info("Rejected agrodealer: {}", agroDealer);
        return CommonResponse.builder()
                .status("00")
                .message("Agrodealer rejected successfully.")
                .build();
    }

    // Get all pending approvals - agrodealers
    public List<AgroDealer> getPendingApprovals() {
        return agroDealerRepository.findByStatus("Pending");
    }
}
