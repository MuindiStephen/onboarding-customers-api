package com.stevemd.onboarding.repository.farmer;


import com.stevemd.onboarding.model.cropcycle.CropCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CropCycleRepository extends JpaRepository<CropCycle, Long > {

    Optional<CropCycle> findFarmFieldByCropName(String cropName);

}
