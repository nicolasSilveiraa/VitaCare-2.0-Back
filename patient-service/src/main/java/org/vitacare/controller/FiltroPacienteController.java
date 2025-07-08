package org.vitacare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vitacare.dto.PacienteCreateRequest;
import org.vitacare.dto.PacienteFilterRequest;
import org.vitacare.model.Enum.StatusDoPaciente;
import org.vitacare.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
@Validated
@RequiredArgsConstructor
public class FiltroPacienteController {

    private final PacienteService pacienteService;

//    @GetMapping("/lista")
//    public Page<PacienteCreateRequest> listaPaciente (
//            @RequestParam(required = false) String nomePaciente,
//            @RequestParam(required = false) StatusDoPaciente statusPaciente,
//            @RequestParam(required = false) Long idPaciente,
//            @RequestParam(required = false) String cpf,
//            @RequestParam(required = false) String search,
//            @PageableDefault(page = 0, size = 10, sort = "nome")Pageable pageable
//    ) {
//        PacienteFilterRequest filtro = new PacienteFilterRequest();
//        filtro.setNomePaciente(nomePaciente);
//        filtro.setStatusDoPaciente(statusPaciente);
//        filtro.setCpf(cpf);
//        filtro.setId(idPaciente);
//        filtro.setSearch(search);
//
//        return pacienteService.getFiltro(filtro, pageable);
//    }
}
