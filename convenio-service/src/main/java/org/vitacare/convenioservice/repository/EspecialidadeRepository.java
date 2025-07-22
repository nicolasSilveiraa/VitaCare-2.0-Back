package org.vitacare.convenioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vitacare.convenioservice.model.Especialidade;
import org.vitacare.dtos.healthplan.EspecialidadeEnum;

import java.util.Optional;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
    Optional<Especialidade> findByNome(EspecialidadeEnum nome);

}
