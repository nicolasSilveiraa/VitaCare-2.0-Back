package org.vitacare.emergenciaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vitacare.emergenciaservice.model.AtendimentoEmergenciaModel;
import org.vitacare.emergenciaservice.service.PaginacaoService;

import java.util.List;

@RestController
@RequestMapping("/pagina")

public class PaginacaoController {
    @Autowired
    private PaginacaoService paginacaoService;

    @GetMapping
    public List<AtendimentoEmergenciaModel> paginas(@PageableDefault(page = 0, size = 5, sort = "idPaciente") Pageable pageable) {

        return paginacaoService.paginacaoPacienteRequests(pageable).getContent();
    }

}
