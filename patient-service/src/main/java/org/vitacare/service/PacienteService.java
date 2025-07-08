package org.vitacare.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vitacare.dto.PacienteCreateRequest;
import org.vitacare.dto.PacienteFilterRequest;
import org.vitacare.model.Enum.StatusDoPaciente;
import org.vitacare.model.PacienteModel;
import org.vitacare.repository.PacienteRepository;

import java.util.List;


@Service
@RequiredArgsConstructor

public class PacienteService {

    private final PacienteRepository paciente;
    private final ObjectMapper objectMapper;
    private final PacienteRepository pacienteRepository;

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

    public void validacaoStatusPaciente(PacienteCreateRequest pacienteCreateRequest) throws Exception {
        if (pacienteCreateRequest.getStatusPaciente() != null && !pacienteCreateRequest.getStatusPaciente().equals(StatusDoPaciente.CONSULTA_REALIZADA)) {
            throw new Exception("Paciente não finalizou a consulta"); //TODO fazer a validação ainda, está incorreto a lógica ele vai vir nulo do banco
        }
    }

    public void adicionarPaciente(PacienteCreateRequest pacienteCreateRequest) throws Exception{
        verificarPacienteCriado(pacienteCreateRequest);
        validacaoStatusPaciente(pacienteCreateRequest);
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


//TODO Realizar a construção do filtro
//    public Page<PacienteCreateRequest> getFiltro(PacienteFilterRequest filtro, Pageable pageable) {
//
//        String nomePaciente = extractNomePaciente(filtro);
//        String cpf = extractCpfPaciente(filtro);
//        StatusDoPaciente statusDoPaciente = extractStatusPaciente(filtro);
//        String search = filtro.getSearch();
//
//
//        String busca = filtro.getSearch();
//        if (busca != null && !busca.isEmpty()) {
//            busca = "%" + busca + "%";
//        }else {
//            busca = null;
//        }
//        Page<PacienteCreateRequest> results = pacienteRepository.
//
//    }
//
//    private String extractNomePaciente(PacienteFilterRequest filtro) {
//        return (filtro != null) ? filtro.getNomePaciente() : null;
//    }
//
//    private String extractCpfPaciente(PacienteFilterRequest filtro) {
//        return (filtro != null) ? filtro.getCpf() : null;
//    }
//
//    private StatusDoPaciente extractStatusPaciente(PacienteFilterRequest filtro) {
//        return (filtro != null) ? filtro.getStatusDoPaciente() : null;
//    }



}
