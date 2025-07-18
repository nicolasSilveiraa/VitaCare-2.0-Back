package org.vitacare.convenioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vitacare.convenioservice.model.Planos;

@Repository
public interface PlanoRepository extends JpaRepository<Planos, Long> {

}
