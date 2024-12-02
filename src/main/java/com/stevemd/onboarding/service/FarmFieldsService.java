package com.stevemd.onboarding.service;


import com.stevemd.onboarding.model.farmfield.FarmField;
import com.stevemd.onboarding.repository.FarmFieldsRepository;
import com.stevemd.onboarding.wrappers.response.FarmFieldResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FarmFieldsService {

    private final FarmFieldsRepository farmFieldsRepository;

    @Autowired
    public FarmFieldsService(FarmFieldsRepository farmFieldsRepository) {
        this.farmFieldsRepository = farmFieldsRepository;
    }

    public List<FarmField> farmFields() {
        return farmFieldsRepository.findAll();
    }

    // add a new farm field
    public FarmFieldResponse addNewFarmField(FarmField farmField) {

        Optional<FarmField> farmFieldByName =
                farmFieldsRepository.findFarmFieldByFarmName(farmField.getFarmName());

        if (farmFieldByName.isPresent()) {

           return FarmFieldResponse.builder()
                    .status("1")
                    .message("Farm already exist. Farm field is not created.")
                    .build();


            //throw new IllegalStateException("The Farm already exists.");
        }


        farmFieldsRepository.save(farmField);

        System.out.println("Created a new farm field: "+farmField);

        log.info(String.valueOf(HttpStatus.CREATED));

        return FarmFieldResponse.builder()
                .status("00")
                .message("Farm Created successfully.")
                .build();

    }

    public void deleteFarmField(Long farmId) {
        boolean exists = farmFieldsRepository.existsById(farmId);
        if (!exists) {
            throw new IllegalStateException("Farmer with id:" + farmId + " does not exist.");
        }
        farmFieldsRepository.deleteById(farmId);
    }

    @Transactional
    public void updateFarmField(Long userId,String name,String email) {

        FarmField farmField = farmFieldsRepository.findById(userId)
                .orElseThrow(()-> new IllegalStateException(
                        "Farm with id: "+userId+" does not exist."
                ));

//        if (name!=null && !name.isEmpty() && !Objects.equals(user.getName(),name)){
//            user.setName(name);
//
//        }
//        if (email!=null && !email.isEmpty() && !Objects.equals(user.getEmail(),email)){
//
//            Optional <User> userUpdate = userRepository.findByEmail(email);
//
//            if (userUpdate.isPresent()) {
//                throw new IllegalStateException("Email is already in use by another account.");
//            }
//            user.setEmail(email);
//        }
    }


}
