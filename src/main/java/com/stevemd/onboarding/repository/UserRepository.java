package com.stevemd.onboarding.repository;

import com.stevemd.onboarding.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByEmail(String email);
    Boolean existsByName(String name);

    Optional<User> findByEmail(String email);
    Optional<User> findByNameOrEmail(String name, String email);
    User findByName(String name);

    User findFirstByEmail(String email);
}
