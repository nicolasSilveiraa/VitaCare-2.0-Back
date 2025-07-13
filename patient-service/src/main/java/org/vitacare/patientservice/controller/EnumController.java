package org.vitacare.patientservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vitacare.patientservice.model.Enum.ConvenioDoPaciente;
import org.vitacare.patientservice.model.Enum.EstadoCivilDoPaciente;
import org.vitacare.patientservice.model.Enum.SexoDoPaciente;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/v1/patients")
public class EnumController {

    @GetMapping("/convenios")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_RECEPTIONIST', 'ROLE_NURSE', 'ROLE_USER')")
    public List<String> getConvenios() {
        return Arrays.stream(ConvenioDoPaciente.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/estadocivil")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_RECEPTIONIST', 'ROLE_NURSE', 'ROLE_USER')")
    public List<String> getEstadoCivil() {
        return Arrays.stream(EstadoCivilDoPaciente.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/sexos")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_RECEPTIONIST', 'ROLE_NURSE', 'ROLE_USER')")
    public List<String> getSexos() {
        return Arrays.stream(SexoDoPaciente.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
