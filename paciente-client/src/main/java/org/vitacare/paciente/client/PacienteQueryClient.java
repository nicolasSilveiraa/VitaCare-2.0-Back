package org.vitacare.paciente.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.vitacare.dtos.patient.PacienteResponse;
import org.vitacare.dtos.patient.PacienteSummaryDTO;

@FeignClient(name = "paciente-comando-service")
public interface PacienteQueryClient {

    @GetMapping("/api/v1/pacientes/{id}")
    PacienteResponse getPacienteById(@PathVariable("id") Long id);

    @GetMapping("/api/v1/pacientes/{id}/summary")
    PacienteSummaryDTO getPacienteSummaryById(@PathVariable("id") Long id);
}
