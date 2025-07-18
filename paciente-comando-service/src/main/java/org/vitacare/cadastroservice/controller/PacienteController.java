package org.vitacare.cadastroservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.vitacare.dtos.patient.PacienteCreateRequest;
import org.vitacare.cadastroservice.model.PacienteModel;
import org.vitacare.cadastroservice.service.PacienteService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    public List<PacienteModel> getPaciente() {
        return pacienteService.buscarPaciente();
    }

    @GetMapping("/{id}")
    public PacienteModel getPacienteId(@PathVariable Long id) throws Exception{
        return pacienteService.buscarPacientePorId(id);
    }

    @PostMapping
    public void adicionarPaciente(@RequestBody PacienteCreateRequest pacienteCreateRequest) throws Exception {
        pacienteService.cadastrarPaciente(pacienteCreateRequest);
    }

    @PutMapping("/{id}")
    public void atualizarPaciente(@RequestBody PacienteCreateRequest pacienteCreateRequest, @PathVariable Long id) throws Exception {
        pacienteService.alterarPaciente(pacienteCreateRequest, id);
    }

    @DeleteMapping("/{id}")
    public void excluirPaciente(@PathVariable Long id) throws Exception {
        pacienteService.excluirPaciente(id);
    }

}
