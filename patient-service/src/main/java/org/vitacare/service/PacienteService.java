package org.vitacare.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vitacare.dto.PacienteCreateRequest;
import org.vitacare.model.PacienteModel;
import org.vitacare.repository.PacienteRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


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

    public void atualizarPaciente(Long id, PacienteCreateRequest pacienteCreateRequest) throws Exception{
        Optional<PacienteModel>pacienteExiste = paciente.findById(id);

        if (pacienteExiste.isEmpty()) {
            throw new Exception("Paciente com o id: " + id + " não encontrado");
        }
        PacienteModel pacienteModel = objectMapper.convertValue(pacienteCreateRequest, PacienteModel.class);
        pacienteModel.setIdPaciente(id);
        paciente.save(pacienteModel);
    }

    public void removerPaciente(Long id) {

        if (!paciente.existsById(id)) {
            throw new EntityNotFoundException("Paciente com ID " + id + " não encontrado, não foi possível remover.");
        }

        paciente.deleteById(id);
    }

}
