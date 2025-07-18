package org.vitacare.convenioservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.vitacare.dtos.healthplan.PlanosRequest;
import org.vitacare.convenioservice.model.Planos;
import org.vitacare.convenioservice.service.PlanoService;

import java.util.List;

@RestController
@RequestMapping("api/v1/planos")
@RequiredArgsConstructor
public class PlanoController {

    private final PlanoService planoService;

    @GetMapping
    public List<Planos> getListaPlanos() {
        return planoService.listaPlanos();
    }

    @GetMapping("{id}")
    public Planos buscarPlanoPorId(@PathVariable Long id) throws Exception{
        return planoService.buscarPlanoPorId(id);
    }

    @PostMapping
    public void cadastrarPlano(@RequestBody PlanosRequest planosRequest) throws Exception {
        planoService.cadastrarPlano(planosRequest);
    }

    @PutMapping("{id}")
    public void atualizarPlano(@RequestBody PlanosRequest planosRequest, @PathVariable Long id) throws Exception {
        planoService.atualizarPlano(id, planosRequest);
    }

    @DeleteMapping("{id}")
    public void deletarPlano(@PathVariable Long id) throws Exception {
        planoService.deletarPlano(id);
    }


}
