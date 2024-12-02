package com.stevemd.onboarding.repository;

import com.stevemd.onboarding.model.farmfield.FarmField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface FarmFieldsRepository extends JpaRepository<FarmField, Long> {

    Optional<FarmField> findFarmFieldByFarmName(String farmName);

}
