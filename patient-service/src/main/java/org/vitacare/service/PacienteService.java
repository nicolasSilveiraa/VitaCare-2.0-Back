package org.vitacare.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vitacare.dto.PacienteCreateRequest;
import org.vitacare.dto.TriagemPacienteRequest;
import org.vitacare.exception.*;
import org.vitacare.model.Enum.StatusDoPaciente;
import org.vitacare.model.PacienteModel;
import org.vitacare.repository.PacienteRepository;

import java.util.List;
import java.util.Optional;


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
        PacienteModel consultaPaciente = verificarTriagemRealizada(id);
        consultaPaciente.setStatusPaciente(StatusDoPaciente.AGUARDANDO_CONSULTA);
        pacienteRepository.save(consultaPaciente);
    }

    public PacienteModel verificarTriagemRealizada(Long id) throws Exception{
        Optional<PacienteModel> triagemPaciente = pacienteRepository.findById(id);
        if (triagemPaciente.isEmpty() || !triagemPaciente.get().getStatusPaciente().equals(StatusDoPaciente.AGUARDANDO_TRIAGEM)) {
            throw new TriagemStatusException();
        }
        return triagemPaciente.get();
    }

    public void realizarConsulta(Long id) throws Exception {
        verificarPacienteExiste(id);
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
