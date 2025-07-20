package org.vitacare.pacientecomandoservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vitacare.dtos.patient.PacienteCreateRequest;
import org.vitacare.dtos.healthplan.PlanosResponse;
import org.vitacare.dtos.patient.PacienteResponse;
import org.vitacare.dtos.patient.PacienteSummaryDTO;
import org.vitacare.pacientecomandoservice.exception.InvalidRequestException;
import org.vitacare.pacientecomandoservice.exception.PacienteCadastradoException;
import org.vitacare.pacientecomandoservice.exception.PacienteExisteException;
import org.vitacare.pacientecomandoservice.model.PacienteModel;
import org.vitacare.convenio.client.ConvenioClient;
import org.vitacare.pacientecomandoservice.repository.PacienteRepository;

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
        Boolean byCpf = paciente.existsByCpf(pacienteCreateRequest.cpf());
        if(byCpf) {
            throw new PacienteCadastradoException();
        }
    }

    public void cadastrarPaciente(PacienteCreateRequest pacienteCreateRequest) throws Exception{
        verificarPacienteCriado(pacienteCreateRequest);
        PacienteModel pacienteModel = objectMapper.convertValue(pacienteCreateRequest, PacienteModel.class);
        if (pacienteCreateRequest.convenio() && pacienteCreateRequest.idPlano() != null) {
            try {
                convenioClient.buscarPlanoPorId(pacienteCreateRequest.idPlano());
            } catch (FeignException.NotFound e) {
                throw new InvalidRequestException("O plano de saude com ID " +  pacienteCreateRequest.idPlano() + "não foi encontrado.");
            } catch (FeignException.Forbidden e) {
                throw new InvalidRequestException("Acesso negado ao buscar dados do paciente com ID " + ". Verifique as permissões.");
            }
            PlanosResponse planos = convenioClient.buscarPlanoPorId(pacienteCreateRequest.idPlano());
            pacienteModel.setIdPlano(planos.id());
        }else {
            pacienteModel.setIdPlano(null);
        }
        paciente.save(pacienteModel);
    }

    public void alterarPaciente(PacienteCreateRequest pacienteCreateRequest, Long id) throws Exception {
        verificarPacienteExiste(id);
        PacienteModel pacienteModel = objectMapper.convertValue(pacienteCreateRequest, PacienteModel.class);
        PlanosResponse planosResponse = convenioClient.buscarPlanoPorId(pacienteCreateRequest.idPlano());
        pacienteModel.setIdPaciente(planosResponse.id());
        paciente.save(pacienteModel);
    }

    public void excluirPaciente(Long id) throws Exception {
        verificarPacienteExiste(id);
        paciente.deleteById(id);
    }

    public void alterarPlanoDoPaciente(PacienteCreateRequest pacienteCreateRequest, Long id) throws Exception {
        verificarPacienteExiste(id);


    }

    public PacienteSummaryDTO buscarPacienteSummaryPorId(Long id) throws Exception {
        PacienteModel paciente = buscarPacientePorId(id);

        return new PacienteSummaryDTO(
                paciente.getIdPaciente(),
                paciente.getNomePaciente()
        );
    }

    private PacienteResponse convertToResponseDTO(PacienteModel paciente) {
        return new PacienteResponse(
                paciente.getIdPaciente(),
                paciente.getNomePaciente(),
                paciente.getDataNascimento(),
                paciente.getSexoPaciente(),
                paciente.getEndereco(),
                paciente.getConvenio(),
                paciente.getIdPlano(),
                paciente.getCpf()
        );
    }


}
