package org.vitacare.paciente.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.vitacare.dtos.patient.PacienteResponse;
import org.vitacare.dtos.patient.PacienteSummaryDTO;

import java.util.List;

@FeignClient(name = "paciente-comando-service", path = "/api/v1/pacientes")
public interface PacienteQueryClient {

    @GetMapping("/{id}")
    PacienteResponse getPacienteById(@PathVariable("id") Long id);

    @GetMapping("/{id}/summary")
    PacienteSummaryDTO getPacienteSummaryById(@PathVariable("id") Long id);

    @GetMapping("/api/v1/pacientes")
    List<PacienteSummaryDTO> getAllPacientes();
}
