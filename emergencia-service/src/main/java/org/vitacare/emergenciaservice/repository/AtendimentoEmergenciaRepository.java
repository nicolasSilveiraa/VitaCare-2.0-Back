package org.vitacare.emergenciaservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Repository;
import org.vitacare.dtos.patient.emergencia.AtendimentoEmergenciaResponse;
import org.vitacare.dtos.patient.emergencia.StatusAtendimento;
import org.vitacare.emergenciaservice.model.AtendimentoEmergenciaModel;

import java.util.List;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@Repository
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public interface AtendimentoEmergenciaRepository extends JpaRepository<AtendimentoEmergenciaModel, Long> {

    Page<AtendimentoEmergenciaModel> findByStatus(StatusAtendimento status, Pageable pageable);

    List<AtendimentoEmergenciaModel> findAllByPacienteId(Long pacienteId);
}
