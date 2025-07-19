package org.vitacare.convenio.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.vitacare.dtos.healthplan.PlanosRequest;
import org.vitacare.dtos.healthplan.PlanosResponse;

import java.util.List;

@FeignClient(name = "convenio-service")
public interface ConvenioClient {

    @GetMapping("/api/v1/planos")
    List<PlanosResponse> getListaPlanos();

    @GetMapping("/api/v1/planos/{id}")
    PlanosResponse buscarPlanoPorId(@PathVariable Long id);

    @PostMapping("/api/v1/planos")
    void cadastrarPlano(@RequestBody PlanosRequest planosRequest);

    @PutMapping("/api/v1/planos/{id}")
    void atualizarPlano(@RequestBody PlanosRequest planosRequest, @PathVariable Long id);

    @DeleteMapping("/api/v1/planos/{id}")
    void deletarPlano(@PathVariable Long id);

}
