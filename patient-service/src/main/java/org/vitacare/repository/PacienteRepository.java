package org.vitacare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vitacare.model.PacienteModel;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {

    Boolean existsByNomePaciente(String nome);
    Boolean existsByCpfPaciente(String cpfPaciente);

}
