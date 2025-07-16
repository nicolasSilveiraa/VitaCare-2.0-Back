package org.vitacare.professionalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vitacare.professionalservice.model.Specialty;

import java.util.Optional;


public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {

    Optional<Specialty> findByName(String name);
}
