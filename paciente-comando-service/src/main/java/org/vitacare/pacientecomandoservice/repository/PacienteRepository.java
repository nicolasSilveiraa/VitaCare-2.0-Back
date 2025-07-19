package org.vitacare.pacientecomandoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vitacare.pacientecomandoservice.model.PacienteModel;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {

    Boolean existsByCpf(String cpf);

}
