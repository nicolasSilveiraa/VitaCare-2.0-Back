package org.vitacare.professionalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.vitacare.professionalservice.model.Professional;

import java.util.Optional;


public interface ProfessionalRepository extends JpaRepository<Professional, Long>,
        JpaSpecificationExecutor<Professional> {

    Optional<Professional> findByProfessionalLicense(String license);
}