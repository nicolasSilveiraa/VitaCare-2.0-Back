package org.vitacare.convenioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vitacare.convenioservice.model.EspecialidadeDoPlanoModel;

@Repository
public interface EspecialidadeDoPlanoRepository extends JpaRepository<EspecialidadeDoPlanoModel, Long> {
}
