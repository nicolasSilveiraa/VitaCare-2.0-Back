package org.vitacare.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jshell.Diag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.vitacare.dto.ConsultaPacienteRequest;
import org.vitacare.dto.PacienteCreateRequest;
import org.vitacare.dto.PaginacaoPacienteRequest;
import org.vitacare.dto.TriagemPacienteRequest;
import org.vitacare.exception.*;
import org.vitacare.model.Enum.StatusDoPaciente;
import org.vitacare.model.PacienteModel;
import org.vitacare.repository.PacienteRepository;
//import org.vitacare.repository.PaginacaoRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository paciente;
    private final ObjectMapper objectMapper;
    private final PacienteRepository pacienteRepository;
//    private final PaginacaoRepository paginacaoRepository;

    public List<PacienteModel> buscarPaciente(){
        return paciente.findAll();
    }

    public PacienteModel buscarPacientePorId(Long id) throws Exception {
        verificarPacienteExiste(id);
        return paciente.findById(id).get();
    }

    public void verificarPacienteExiste(Long id) throws Exception{
        if (!paciente.existsById(id)) {
            throw new PacienteNaoEncontradoException(id);
        }
    }

    private void verificarPacienteCriado(PacienteCreateRequest pacienteCreateRequest) throws Exception {
        Boolean byNome = paciente.existsByNomePaciente(pacienteCreateRequest.getNomePaciente());
        if (byNome) {
            throw new PacienteRegistradoException();
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

    public void realizarTriagem(Long id, TriagemPacienteRequest triagemPacienteRequest) throws Exception {
        verificarPacienteExiste(id);
        cadastramentoTriagem(id, triagemPacienteRequest);
        Optional<PacienteModel> pacienteModel = pacienteRepository.findById(id);
        PacienteModel paciente = pacienteModel.get();
        verificarTriagemRealizada(paciente);
        paciente.setStatusPaciente(StatusDoPaciente.AGUARDANDO_CONSULTA);
        pacienteRepository.save(paciente);
    }

    public void verificarTriagemRealizada(PacienteModel pacienteModel) throws Exception{
        if (!pacienteModel.getStatusPaciente().equals(StatusDoPaciente.AGUARDANDO_TRIAGEM)) {
            throw new TriagemStatusException();
        }
    }

    public void realizarConsulta(Long id, ConsultaPacienteRequest consultaPacienteRequest) throws Exception {
        verificarPacienteExiste(id);
        cadastramentoConsulta(id, consultaPacienteRequest);
        PacienteModel realizandoConsulta = verificarConsultaRealizada(id);
        realizandoConsulta.setStatusPaciente(StatusDoPaciente.CONSULTA_REALIZADA);
        pacienteRepository.save(realizandoConsulta);
    }

    public PacienteModel verificarConsultaRealizada(Long id) throws Exception {
        Optional<PacienteModel> consultaPaciente = pacienteRepository.findById(id);
        if (consultaPaciente.isEmpty() || !consultaPaciente.get().getStatusPaciente().equals(StatusDoPaciente.AGUARDANDO_CONSULTA)) {
            throw new ConsultaStatusException();
        }
        return consultaPaciente.get();
    }

    //TODO Consertar os if encadeado
    public PacienteModel cadastramentoTriagem(Long id, TriagemPacienteRequest triagemPacienteRequest) throws Exception {
       Optional<PacienteModel> cadastroTriagem = pacienteRepository.findById(id);

       if (cadastroTriagem.isEmpty() || triagemPacienteRequest.getAlergiasPaciente() == null) {
            throw new AlergiaVazioException();
        }
        if (cadastroTriagem.isEmpty() || triagemPacienteRequest.getQueixasPaciente() == null) {
            throw new QueixasVazioException();
        }

        cadastroTriagem.get().setAlergiasPaciente(triagemPacienteRequest.getAlergiasPaciente());
        cadastroTriagem.get().setQueixasPaciente(triagemPacienteRequest.getQueixasPaciente());
        return pacienteRepository.save(cadastroTriagem.get());
    }

    public PacienteModel cadastramentoConsulta(Long id, ConsultaPacienteRequest consultaPacienteRequest) throws Exception {
        Optional<PacienteModel> cadastroConsulta = pacienteRepository.findById(id);

        if (cadastroConsulta.isEmpty() || consultaPacienteRequest.getDiagnosticoPaciente() == null) {
            throw new DiagnosticoVazioException();
        }
        if (cadastroConsulta.isEmpty() || consultaPacienteRequest.getPrescricaoPaciente() == null) {
            throw new PrescricaoVazioException();
        }
        cadastroConsulta.get().setDiagnosticoPaciente(consultaPacienteRequest.getDiagnosticoPaciente());
        cadastroConsulta.get().setPrescricaoPaciente(consultaPacienteRequest.getPrescricaoPaciente());
        return pacienteRepository.save(cadastroConsulta.get());
    }

//    public Page<PaginacaoPacienteRequest> paginacaoPacienteRequests() {
//
//    }



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
