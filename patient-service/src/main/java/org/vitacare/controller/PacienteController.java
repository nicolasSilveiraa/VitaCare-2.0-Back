package org.vitacare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.vitacare.dto.PacienteCreateRequest;
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

    @PostMapping
    public void adicionarPaciente(@RequestBody PacienteCreateRequest pacienteCreateRequest) throws Exception{
        pacienteService.adicionarPaciente(pacienteCreateRequest);
    }

}
