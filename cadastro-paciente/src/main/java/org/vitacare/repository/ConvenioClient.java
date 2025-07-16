package org.vitacare.repository;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.vitacare.dto.response.PlanoResponse;

@FeignClient(name = "convenio-paciente", url = "http://localhost:8082")
public interface ConvenioClient {

    @GetMapping("/{id}")
    ResponseEntity<PlanoResponse>


}
