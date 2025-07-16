package org.vitacare.repository;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.vitacare.dto.request.PlanosRequest;
import org.vitacare.dto.response.PlanosResponse;
import org.vitacare.model.Planos;

import java.util.List;

@FeignClient(name = "convenio-paciente", url = "http://localhost:8082")
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
