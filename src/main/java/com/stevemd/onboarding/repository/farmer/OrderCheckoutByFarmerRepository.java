package com.stevemd.onboarding.repository.farmer;

import com.stevemd.onboarding.model.farmerorder.OrderCheckoutByFarmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJpaRepositories
public interface OrderCheckoutByFarmerRepository extends JpaRepository<OrderCheckoutByFarmer, Long> {

    // Find orders by AgroDealer ID
    List<OrderCheckoutByFarmer> findByAgrodealerID(String agrodealerID);
}
