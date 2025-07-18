package org.vitacare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vitacare.model.EspecialidadeDoPlanoModel;

@Repository
public interface EspecialidadeDoPlanoRepository extends JpaRepository<EspecialidadeDoPlanoModel, Long> {
}
