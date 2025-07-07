package org.vitacare.authservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.vitacare.authservice.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);
}
