package org.vitacare.patientservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.vitacare.patientservice.dto.ConsultaPacienteRequest;
import org.vitacare.patientservice.dto.FIltroPacienteResponse;
import org.vitacare.patientservice.dto.PacienteCreateRequest;
import org.vitacare.patientservice.dto.TriagemPacienteRequest;
import org.vitacare.patientservice.model.Enum.StatusDoPaciente;
import org.vitacare.patientservice.model.PacienteModel;
import org.vitacare.patientservice.service.PacienteService;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping()
    public List<PacienteModel> getPaciente(){
        return pacienteService.buscarPaciente();
    }

    @GetMapping("/{id}")
    public PacienteModel buscarPacientePorId(@PathVariable Long id) throws Exception{
       return pacienteService.buscarPacientePorId(id);
    }

    @GetMapping("/filtro")
    public Page<FIltroPacienteResponse> filtrar (
            @RequestParam(required = false) Long idPaciente,
            @RequestParam(required = false) String nomePaciente,
            @RequestParam(required = false) LocalDate dataNascimento,
            @RequestParam(required = false) String cpfPaciente,
            @RequestParam(required = false)StatusDoPaciente statusDoPaciente,
            @PageableDefault(size = 10, sort = "idPaciente", direction = Sort.Direction.ASC) Pageable pageable
            ) {
        return pacienteService.filtrarPaciente(idPaciente, nomePaciente, dataNascimento, cpfPaciente, statusDoPaciente, pageable);
    }


    @PostMapping
    public void adicionarPaciente(@RequestBody PacienteCreateRequest pacienteCreateRequest) throws Exception{
        pacienteService.adicionarPaciente(pacienteCreateRequest);
    }

    @PutMapping("/{id}")
    public void editarFuncionario(@RequestBody PacienteCreateRequest pacienteCreateRequest, @PathVariable Long id) throws Exception {
      pacienteService.atualizarPaciente(id, pacienteCreateRequest);
    }

    @DeleteMapping("/{id}")
    public void removerFuncionario(@PathVariable Long id) throws Exception{
        pacienteService.removerPaciente(id);
    }

    @PatchMapping("/triagem/{id}")
    public void realizarTriagem(@PathVariable Long id, @RequestBody TriagemPacienteRequest triagemPacienteRequest) throws Exception{
        pacienteService.realizarTriagem(id, triagemPacienteRequest);
    }

    @PatchMapping("/consulta/{id}")
    public void realizarConsulta(@PathVariable Long id, @RequestBody ConsultaPacienteRequest consultaPacienteRequest) throws Exception {
        pacienteService.realizarConsulta(id, consultaPacienteRequest);
    }
}
