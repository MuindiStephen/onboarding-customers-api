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

    public List<AgroDealer> agroDealers() {
        return agroDealerRepository.findAll();
    }

    // add a new farm field
    public CommonResponse addNewAgrodealer(AgroDealer agroDealer) {

        Optional<AgroDealer> agroDealerByAgrodealerName =
                agroDealerRepository.findAgroDealerByAgrodealerName(agroDealer.getAgrodealerName());

        if (agroDealerByAgrodealerName.isPresent()) {

            return CommonResponse.builder()
                    .status("1")
                    .message("Similar agrodealer exist!")
                    .build();


            //throw new IllegalStateException("The Farm already exists.");
        }


        agroDealerRepository.save(agroDealer);

        System.out.println("Created a new agrodealer: "+agroDealer);

        log.info(String.valueOf(HttpStatus.CREATED));

        return CommonResponse.builder()
                .status("00")
                .message("Agrodealer Created successfully.")
                .build();

    }
}
