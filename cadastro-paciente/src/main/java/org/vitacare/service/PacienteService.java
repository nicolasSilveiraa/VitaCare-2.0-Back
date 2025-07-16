package org.vitacare.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vitacare.dto.request.PacienteCreateRequest;
import org.vitacare.dto.response.PlanosResponse;
import org.vitacare.exception.PacienteCadastradoException;
import org.vitacare.exception.PacienteExisteException;
import org.vitacare.model.PacienteModel;
import org.vitacare.model.Planos;
import org.vitacare.repository.ConvenioClient;
import org.vitacare.repository.PacienteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository paciente;
    private final ObjectMapper objectMapper;
    private final ConvenioClient convenioClient;

    public List<PacienteModel> buscarPaciente() {
        return paciente.findAll();
    }

    public PacienteModel buscarPacientePorId(Long id) throws Exception {
        verificarPacienteExiste(id);
        return paciente.findById(id).get();
    }

    public void verificarPacienteExiste(Long id) throws Exception {
        if (!paciente.existsById(id)) {
            throw new PacienteExisteException(id);
        }
    }

    public void verificarPacienteCriado(PacienteCreateRequest pacienteCreateRequest) throws Exception {
        Boolean byCpf = paciente.existsByCpf(pacienteCreateRequest.getCpf());
        if(byCpf) {
            throw new PacienteCadastradoException();
        }
    }

    public void cadastrarPaciente(PacienteCreateRequest pacienteCreateRequest) throws Exception{
        verificarPacienteCriado(pacienteCreateRequest);
        PlanosResponse planos = convenioClient.buscarPlanoPorId(pacienteCreateRequest.getIdPlano());
        PacienteModel pacienteModel = objectMapper.convertValue(pacienteCreateRequest, PacienteModel.class);
        pacienteModel.setIdPlano(planos.getId());
        paciente.save(pacienteModel);
    }

    public void alterarPaciente(PacienteCreateRequest pacienteCreateRequest, Long id) throws Exception {
        verificarPacienteExiste(id);
        PacienteModel pacienteModel = objectMapper.convertValue(pacienteCreateRequest, PacienteModel.class);
        pacienteModel.setIdPaciente(id);
        paciente.save(pacienteModel);
    }

    public void excluirPaciente(Long id) throws Exception {
        verificarPacienteExiste(id);
        paciente.deleteById(id);
    }


}
