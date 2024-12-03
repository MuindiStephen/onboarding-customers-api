package com.stevemd.onboarding.repository;

import com.stevemd.onboarding.model.agrodealer.AgroDealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@EnableJpaRepositories
public interface AgroDealerRepository extends JpaRepository<AgroDealer,Long> {

    Optional<AgroDealer> findAgroDealerByAgrodealerName(String agrodealerName);
}
