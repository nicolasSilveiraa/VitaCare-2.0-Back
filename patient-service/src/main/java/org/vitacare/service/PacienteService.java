package org.vitacare.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vitacare.dto.PacienteCreateRequest;
import org.vitacare.model.PacienteModel;
import org.vitacare.repository.PacienteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PacienteService {

    private final PacienteRepository paciente;
    private final ObjectMapper objectMapper;

    public List<PacienteModel> buscarPaciente(){
        return paciente.findAll();
    }

    public void adicionarPaciente(PacienteCreateRequest pacienteCreateRequest) throws Exception{
        Boolean byNome = paciente.existsByNomePaciente(pacienteCreateRequest.getNomePaciente());
        if (byNome) {
            throw new Exception("Paciente já registrado");
        }
        PacienteModel pacienteModel = objectMapper.convertValue(pacienteCreateRequest, PacienteModel.class);
        paciente.save(pacienteModel);
    }



}
