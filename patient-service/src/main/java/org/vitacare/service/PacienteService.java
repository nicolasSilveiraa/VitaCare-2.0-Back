package org.vitacare.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vitacare.dto.PacienteCreateRequest;
import org.vitacare.model.Enum.StatusDoPaciente;
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

    public PacienteModel buscarPacientePorId(Long id) throws Exception {
        verificarPacienteExiste(id);
        return paciente.findById(id).get();
    }

    public void verificarPacienteExiste(Long id) throws Exception{
        if (!paciente.existsById(id)) {
            throw new Exception("Paciente com o id: " + id + " não encontrado");
        }
    }

    private void verificarPacienteCriado(PacienteCreateRequest pacienteCreateRequest) throws Exception {
        Boolean byNome = paciente.existsByNomePaciente(pacienteCreateRequest.getNomePaciente());
        if (byNome) {
            throw new Exception("Paciente já registrado");
        }
    }

    public void adicionarPaciente(PacienteCreateRequest pacienteCreateRequest) throws Exception{
        verificarPacienteCriado(pacienteCreateRequest);
        PacienteModel pacienteModel = objectMapper.convertValue(pacienteCreateRequest, PacienteModel.class);
        pacienteModel.setStatusPaciente(StatusDoPaciente.AGUARDANDO_TRIAGEM);
        paciente.save(pacienteModel);
    }

    public void atualizarPaciente(Long id, PacienteCreateRequest pacienteCreateRequest) throws Exception{
        verificarPacienteExiste(id);
        PacienteModel pacienteModel = objectMapper.convertValue(pacienteCreateRequest, PacienteModel.class);
        pacienteModel.setIdPaciente(id);
        paciente.save(pacienteModel);
    }

    public void removerPaciente(Long id) throws Exception {
        verificarPacienteExiste(id);
        paciente.deleteById(id);
    }

    public void realizarTriagem(Long id, PacienteCreateRequest pacienteCreateRequest) throws Exception {
        verificarPacienteExiste(id);
        verificarTriagemRealizada(pacienteCreateRequest);
        PacienteModel pacienteModel = objectMapper.convertValue(pacienteCreateRequest, PacienteModel.class);
        pacienteModel.setIdPaciente(id);
        pacienteModel.setStatusPaciente(StatusDoPaciente.AGUARDANDO_CONSULTA);
        paciente.save(pacienteModel);
    }

    public void verificarTriagemRealizada(PacienteCreateRequest pacienteCreateRequest) throws Exception{
        PacienteModel pacienteModel = objectMapper.convertValue(pacienteCreateRequest, PacienteModel.class);
        if(!pacienteModel.getStatusPaciente().equals(StatusDoPaciente.AGUARDANDO_TRIAGEM)){
            throw new Exception("Paciente não está aguardando triagem");
        }
    }

    public void realizarConsulta(Long id, PacienteCreateRequest pacienteCreateRequest) throws Exception {
        verificarConsultaRealizada(pacienteCreateRequest);
        PacienteModel pacienteModel = objectMapper.convertValue(pacienteCreateRequest, PacienteModel.class);
        pacienteModel.setIdPaciente(id);
        pacienteModel.setStatusPaciente(StatusDoPaciente.CONSULTA_REALIZADA);
        paciente.save(pacienteModel);
    }

    public void verificarConsultaRealizada(PacienteCreateRequest pacienteCreateRequest) throws Exception {
        PacienteModel pacienteModel = objectMapper.convertValue(pacienteCreateRequest, PacienteModel.class);
        if (!pacienteModel.getStatusPaciente().equals(StatusDoPaciente.AGUARDANDO_CONSULTA)) {
            throw new Exception("Paciente não está aguardando consulta");
        }
    }


}
