package com.stevemd.onboarding.repository.fieldagent;


import com.stevemd.onboarding.model.Role;
import com.stevemd.onboarding.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Role repository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
