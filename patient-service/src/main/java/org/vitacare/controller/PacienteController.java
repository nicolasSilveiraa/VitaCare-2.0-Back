package org.vitacare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.vitacare.dto.PacienteCreateRequest;
import org.vitacare.dto.TriagemRequest;
import org.vitacare.model.PacienteModel;
import org.vitacare.service.PacienteService;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    public List<PacienteModel> getPaciente(){
        return pacienteService.buscarPaciente();
    }

    @GetMapping("/{id}")
    public PacienteModel buscarPacientePorId(@PathVariable Long id) throws Exception{
       return pacienteService.buscarPacientePorId(id);
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
    public void realizarTriagem(@PathVariable Long id) throws Exception{
        pacienteService.realizarTriagem(id);
    }

    @PatchMapping("/consulta/{id}")
    public void realizarConsulta(@PathVariable Long id) throws Exception {
        pacienteService.realizarConsulta(id);
    }



}
