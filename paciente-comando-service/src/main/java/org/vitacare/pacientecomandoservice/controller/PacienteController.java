package org.vitacare.pacientecomandoservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.vitacare.dtos.patient.PacienteCreateRequest;
import org.vitacare.dtos.patient.PacienteSummaryDTO;
import org.vitacare.pacientecomandoservice.model.PacienteModel;
import org.vitacare.pacientecomandoservice.service.PacienteService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@EnableMethodSecurity
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    //TODO: MOVER OS METODOS GET PARA O PACIENTE-CONSULTA-SERVICE
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST', 'ROLE_DOCTOR', 'ROLE_NURSE')")
    public List<PacienteModel> getPaciente() {
        return pacienteService.buscarPaciente();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST', 'ROLE_DOCTOR', 'ROLE_NURSE')")
    public PacienteModel getPacienteId(@PathVariable Long id) throws Exception{
        return pacienteService.buscarPacientePorId(id);
    }

    @GetMapping("/{id}/summary")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST', 'ROLE_DOCTOR', 'ROLE_NURSE')")
    public PacienteSummaryDTO getPacienteSummaryId(@PathVariable Long id) throws Exception{
        return pacienteService.buscarPacienteSummaryPorId(id);
    }


    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST', 'ROLE_DOCTOR', 'ROLE_NURSE')")
    public void adicionarPaciente(@RequestBody PacienteCreateRequest pacienteCreateRequest) throws Exception {
        pacienteService.cadastrarPaciente(pacienteCreateRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST', 'ROLE_DOCTOR', 'ROLE_NURSE')")
    public void atualizarPaciente(@RequestBody PacienteCreateRequest pacienteCreateRequest, @PathVariable Long id) throws Exception {
        pacienteService.alterarPaciente(pacienteCreateRequest, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST', 'ROLE_DOCTOR', 'ROLE_NURSE')")
    public void excluirPaciente(@PathVariable Long id) throws Exception {
        pacienteService.excluirPaciente(id);
    }

}
