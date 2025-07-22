package org.vitacare.convenioservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.vitacare.dtos.healthplan.ConvenioComPlanosRequest;
import org.vitacare.dtos.healthplan.ConvenioRequest;
import org.vitacare.dtos.healthplan.ConvenioComPlanosResponse;
import org.vitacare.convenioservice.model.ConvenioModel;
import org.vitacare.convenioservice.service.ConvenioService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/convenios")
public class ConvenioController {

    private final ConvenioService convenioService;

    // ADICIONAR rota específica para listar
    @GetMapping("/listar")
    public List<ConvenioModel> getConvenio() {
        return convenioService.listaConvenio();
    }

    // Manter a rota para buscar por ID
    @GetMapping("/{id}")
    public ConvenioComPlanosResponse getConvenioPorId(@PathVariable Long id) throws Exception {
        return convenioService.buscarConvenioComPlanosResponse(id);
    }

    @PostMapping
    public void cadastrarConvenio(@RequestBody ConvenioRequest convenioRequest) throws Exception {
        convenioService.cadastrarConvenio(convenioRequest);
    }

    @PostMapping("/com-planos")
    public void cadastrarPlanoComConvenio(@RequestBody ConvenioComPlanosRequest convenioComPlanosRequest) throws Exception {
        convenioService.cadastrarConvenioComPlanos(convenioComPlanosRequest);
    }

    @PutMapping("/{id}")
    public void alterarPlanoComConvenio(@RequestBody ConvenioComPlanosRequest convenioRequest, @PathVariable Long id) throws Exception {
        convenioService.alterarConvenioComPlanos(id, convenioRequest);
    }

    @DeleteMapping("/{id}")
    public void deletarConvenio(@PathVariable Long id) throws Exception {
        convenioService.deletarConvenio(id);
    }
}
