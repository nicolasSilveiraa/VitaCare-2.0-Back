package org.vitacare.emergenciaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vitacare.dtos.patient.emergencia.AtendimentoEmergenciaResponse;
import org.vitacare.dtos.patient.emergencia.StatusAtendimento;
import org.vitacare.emergenciaservice.model.AtendimentoEmergenciaModel;

import java.util.List;

@Repository
public interface AtendimentoEmergenciaRepository extends JpaRepository<AtendimentoEmergenciaModel, Long> {

    List<AtendimentoEmergenciaModel> findByStatus(StatusAtendimento status);

    List<AtendimentoEmergenciaModel> findAllByPacienteId(Long pacienteId);
}
