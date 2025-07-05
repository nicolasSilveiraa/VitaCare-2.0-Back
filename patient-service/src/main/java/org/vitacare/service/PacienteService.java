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

    public PacienteModel atualizarPaciente(Long id, PacienteCreateRequest request) {

        PacienteModel pacienteExistente = paciente.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente com ID " + id + " não encontrado."));

        pacienteExistente.setNomePaciente(request.getNomePaciente());
        pacienteExistente.setDataNascimento(request.getDataNascimento());
        pacienteExistente.setNaturalidadePaciente(request.getNaturalidadePaciente());
        pacienteExistente.setSexoPaciente(request.getSexoPaciente());
        pacienteExistente.setEstadoCivilPaciente(request.getEstadoCivilPaciente());
        pacienteExistente.setCpfPaciente(request.getCpfPaciente());
        pacienteExistente.setRgPaciente(request.getRgPaciente());
        pacienteExistente.setEmissorRgPaciente(request.getEmissorRgPaciente());
        pacienteExistente.setEndereco(request.getEndereco());
        pacienteExistente.setConvenioCliente(request.getConvenioCliente());
        pacienteExistente.setPlanoPaciente(request.getPlanoPaciente());
        pacienteExistente.setValidadeConvenio(request.getValidadeConvenio());
        pacienteExistente.setCarteirinhaPaciente(request.getCarteirinhaPaciente());
        pacienteExistente.setAlergiasPaciente(request.getAlergiasPaciente());
        pacienteExistente.setQueixasPaciente(request.getQueixasPaciente());
        pacienteExistente.setDiagnosticoPaciente(request.getDiagnosticoPaciente());
        pacienteExistente.setPrescricaoPaciente(request.getPrescricaoPaciente());
        pacienteExistente.setStatusPaciente(request.getStatusPaciente());

        return paciente.save(pacienteExistente);
    }

    public void removerPaciente(Long id) {

        if (!paciente.existsById(id)) {
            throw new EntityNotFoundException("Paciente com ID " + id + " não encontrado, não foi possível remover.");
        }

        paciente.deleteById(id);
    }

}
