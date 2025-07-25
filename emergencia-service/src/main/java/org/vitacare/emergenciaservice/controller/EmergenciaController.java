package org.vitacare.emergenciaservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.vitacare.dtos.patient.PacienteSummaryDTO;
import org.vitacare.dtos.patient.emergencia.*;
import org.vitacare.emergenciaservice.service.EmergenciaService;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
public class EmergenciaController {

    private final EmergenciaService emergenciaService;


    @PostMapping("/check-in")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPCIONISTA')")
    public ResponseEntity<AtendimentoEmergenciaResponse> darEntradaPaciente(@Valid @RequestBody CheckInRequest request) {
        AtendimentoEmergenciaResponse response = emergenciaService.darEntradaPaciente(request.pacienteId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{atendimentoId}/triagem")
    @PreAuthorize("hasAnyRole('ROLE_ENFERMEIRA')")
    public ResponseEntity<AtendimentoEmergenciaResponse> realizarTriagem(
            @PathVariable Long atendimentoId,
            @Valid @RequestBody TriagemRequest request) {
        return ResponseEntity.ok(emergenciaService.realizarTriagem(atendimentoId, request));
    }

    @PatchMapping("/{atendimentoId}/consulta")
    @PreAuthorize("hasRole('ROLE_MEDICO')")
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
    public ResponseEntity<Page<FilaAtendimentoResponse>> buscarFilaDeAtendimento(
            @RequestParam String status,
            Pageable pageable) {

        StatusAtendimento statusEnum = StatusAtendimento.valueOf(status.toUpperCase());
        Page<FilaAtendimentoResponse> paginaDeAtendimentos = emergenciaService.buscarFilaPorStatusPaginado(statusEnum, pageable);
        return ResponseEntity.ok(paginaDeAtendimentos);
    }

    @GetMapping("/disponiveis")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ENFERMEIRA', 'ROLE_MEDICO')")
    public ResponseEntity<List<PacienteSummaryDTO>> listarPacientesDisponiveis() {
        return ResponseEntity.ok(emergenciaService.listarPacientesDisponiveis());
    }
}
