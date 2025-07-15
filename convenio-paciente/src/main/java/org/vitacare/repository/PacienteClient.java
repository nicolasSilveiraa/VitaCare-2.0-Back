package org.vitacare.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.vitacare.dto.response.PacienteResponse;

@FeignClient(name = "cadastro-paciente", url = "http://localhost:8086")
public interface PacienteClient {

    @GetMapping("/{id}")
    ResponseEntity<PacienteResponse> buscarPacientePorID(@PathVariable("id") Long id);
}
