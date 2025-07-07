package org.vitacare.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vitacare.authservice.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
