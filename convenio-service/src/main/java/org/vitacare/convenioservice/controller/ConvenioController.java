package org.vitacare.convenioservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.vitacare.dtos.healthplan.ConvenioRequest;
import org.vitacare.convenioservice.model.ConvenioModel;
import org.vitacare.convenioservice.service.ConvenioService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/convenios")
public class ConvenioController {

    private final ConvenioService convenioService;


    @GetMapping
    public List<ConvenioModel> getConvenio() {
        return convenioService.listaConvenio();
    }

    @GetMapping("{id}")
    public ConvenioModel getConvenioPorId(@PathVariable Long id) throws Exception {
        return convenioService.buscarConvenioPorId(id);
    }

    @PostMapping
    public void cadastrarConvenio(@RequestBody ConvenioRequest convenioRequest) throws Exception {
        convenioService.cadastrarConvenio(convenioRequest);
    }

    @PutMapping("{id}")
    public void atualizarConvenio(@RequestBody ConvenioRequest convenioRequest, @PathVariable Long id) throws Exception {
        convenioService.atualizarConvenio(id, convenioRequest);
    }

    @DeleteMapping("{id}")
    public void deletarConvenio(@PathVariable Long id) throws Exception {
        convenioService.deletarConvenio(id);
    }

}
