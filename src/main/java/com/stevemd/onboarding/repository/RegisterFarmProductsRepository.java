package com.stevemd.onboarding.repository;

import com.stevemd.onboarding.model.farmproducts.RegisterFarmProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterFarmProductsRepository extends JpaRepository<RegisterFarmProducts, Long> {

}
