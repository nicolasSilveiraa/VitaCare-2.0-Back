package org.vitacare.pacientecomandoservice.repository;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.vitacare.dtos.healthplan.PlanosRequest;
import org.vitacare.dtos.healthplan.PlanosResponse;

import java.util.List;

@FeignClient(name = "convenio-service")
public interface ConvenioClient {

    @GetMapping("/planos")
    List<PlanosResponse> getListaPlanos();

    @GetMapping("/planos/{id}")
    PlanosResponse buscarPlanoPorId(@PathVariable Long id);

    @PostMapping("/planos")
    void cadastrarPlano(@RequestBody PlanosRequest planosRequest);

    @PutMapping("/planos/{id}")
    void atualizarPlano(@RequestBody PlanosRequest planosRequest, @PathVariable Long id);

    @DeleteMapping("/planos/{id}")
    void deletarPlano(@PathVariable Long id);

}
