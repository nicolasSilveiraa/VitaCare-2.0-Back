package org.vitacare.emergenciaservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.vitacare.dtos.patient.emergencia.*;
import org.vitacare.emergenciaservice.service.EmergenciaService;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
public class EmergenciaController {

    private final EmergenciaService emergenciaService;


    @PostMapping("/check-in")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST')")
    public ResponseEntity<AtendimentoEmergenciaResponse> darEntradaPaciente(@Valid @RequestBody CheckInRequest request) {
        AtendimentoEmergenciaResponse response = emergenciaService.darEntradaPaciente(request.pacienteId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{atendimentoId}/triagem")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<AtendimentoEmergenciaResponse> realizarTriagem(
            @PathVariable Long atendimentoId,
            @Valid @RequestBody TriagemRequest request) {
        return ResponseEntity.ok(emergenciaService.realizarTriagem(atendimentoId, request));
    }

    @PatchMapping("/{atendimentoId}/consulta")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<AtendimentoEmergenciaResponse> realizarConsulta(
            @PathVariable Long atendimentoId,
            @Valid @RequestBody ConsultaRequest request) {
        return ResponseEntity.ok(emergenciaService.realizarConsulta(atendimentoId, request));
    }

    @GetMapping("/{atendimentoId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AtendimentoEmergenciaResponse> buscarAtendimentoPorId(@PathVariable Long atendimentoId) {
        return ResponseEntity.ok(emergenciaService.buscarAtendimentoPorId(atendimentoId));
    }

    @GetMapping("/fila")
    @PreAuthorize("hasAnyRole('NURSE', 'DOCTOR')")
    public ResponseEntity<List<FilaAtendimentoResponse>> buscarFila(@RequestParam("status") StatusAtendimento status) {
        return ResponseEntity.ok(emergenciaService.buscarFilaPorStatus(status));
    }
}
