package org.vitacare.patientservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vitacare.patientservice.model.Enum.ConvenioDoPaciente;
import org.vitacare.patientservice.model.Enum.EstadoCivilDoPaciente;
import org.vitacare.patientservice.model.Enum.SexoDoPaciente;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EnumController {

    @GetMapping("/convenios")
    public List<String> getConvenios() {
        return Arrays.stream(ConvenioDoPaciente.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/sexos")
    public List<String> getSexos() {
        return Arrays.stream(SexoDoPaciente.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/estadocivil")
    public List<String> getEstadoCivil() {
        return Arrays.stream(EstadoCivilDoPaciente.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
