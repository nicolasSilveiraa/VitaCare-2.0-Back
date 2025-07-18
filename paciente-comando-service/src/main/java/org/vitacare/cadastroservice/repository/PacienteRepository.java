package org.vitacare.cadastroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vitacare.cadastroservice.model.PacienteModel;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {

    Boolean existsByCpf(String cpf);

}
